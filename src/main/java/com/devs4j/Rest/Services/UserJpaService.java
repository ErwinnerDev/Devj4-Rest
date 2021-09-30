package com.devs4j.Rest.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.devs4j.Rest.Entity.User;
import com.devs4j.Rest.Repository.UserRepository;

@Service
public class UserJpaService {
	
	@Autowired
	private UserRepository userRepository;
	
	public List<User> getUsers(){
		return userRepository.findAll();
	}
	
	public User getUserById(Integer id) {
		return userRepository.findById(id).orElseThrow(() ->
					new ResponseStatusException(HttpStatus.NO_CONTENT,String.format("no existe el id %d", id))
				);
	}
	
	@Cacheable("users") // para guadra en caheche la respuest, la confifuracipon esta en la clase CacheConfig
	public User getUserByUserName(String userName) {
		try {//se simula una respuest tardada
			Thread.sleep(3000);
		} catch (Exception e) {
			e.printStackTrace(); 
		}
		// la primera vez se tardará el tiempo original
		//pero despues solo regresará lo que ya tiene en cahce y en realidas ya no se ejecuta el metodo 
		//y la respuesta es más rapida
		return userRepository.findByUserName(userName).orElseThrow(() ->
					new ResponseStatusException(HttpStatus.NOT_FOUND,String.format("no existe el usuario %s",userName))
				);
	}
	
	public User getUserByUserNameAndPassword(String usarName,String password) {
		return userRepository.findByUserNameAndPassword(usarName, password).orElseThrow(() ->
			new ResponseStatusException(HttpStatus.NO_CONTENT,"User and password don´t exist")
		);
	}
	
	public Page<User> getUsersPages(int page,int size) {
		return userRepository.findAll(PageRequest.of(page, size));
	}
	
	public List<String> getUserNames(){
		return userRepository.findUserNames();
	}
	
	public Page<String> getUserNamesPages(int page,int size){
		return userRepository.findUserNamesPages(PageRequest.of(page, size));
	}
	
	@CacheEvict("users")
	public void deleteUserByName(String userName) {
		User userByUserName = getUserByUserName(userName);
		userRepository.delete(userByUserName);
	}

}
