package com.devs4j.Rest.Services;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;
import javax.swing.DefaultRowSorter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.devs4j.Rest.Model.User;
import com.github.javafaker.Faker;

@Service
public class UserServices {
	 
	@Autowired
	private Faker faker;
	
	private List<User> users = new  ArrayList<User>();

	public Faker getFaker() {
		return faker;
	}

	public void setFaker(Faker faker) {
		this.faker = faker;
	}
	
	@PostConstruct
	public void init() {
		for(int i=0; i<100; i++) {
			users.add(new User(faker.funnyName().name(),faker.name().name(),faker.dragonBall().character()));
		}
	}
	
	@Cacheable("users") // para guadra en caheche la respuest, la confifuracipon esta en la clase CacheConfig
	public User getUserByUserName(String nameUser){
		
		try {//se simula una respuest tardada
			Thread.sleep(3000);
		} catch (Exception e) {
			e.printStackTrace(); 
		}
		// la primera vez se tardará el tiempo original
		//pero despues solo regresará lo que ya tiene en cahce y en realidas ya no se ejecuta el metodo 
		//y la respuesta es más rapida
		return users.stream().filter(x -> x.getUser().equals(nameUser))
					.findAny().orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,String.format("User %s not fount ",nameUser)));
	}
	
	public List<User> getUsersByUserNameRequestParam(String nameUser) {
		if(nameUser != null) {
			return users.stream().filter(u -> u.getUser().startsWith(nameUser)).collect(Collectors.toList());
		}else {
			return users;
		}
		
	}

	
	public User createUser(User user) {
		if(users.stream().anyMatch(x -> x.getUser().equals(user.getUser()))) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND,String.format("User %s not fout", user.getUser()));
		}
		users.add(user);
		//User user = repository.save(user) // esto se suele usar si se le crea un Id al usuario, pero no es est eel caso
		return user;
	}
	
	public User updateUser(String nameUser,User user) {
		User userByUserName = getUserByUserName(nameUser);
		userByUserName.setNickName(user.getNickName());
		userByUserName.setPassword(user.getPassword());
		return userByUserName;
	}
	
	public void deleteUser(String nameUser) {
		User userByUserName = getUserByUserName(nameUser);
		users.remove(userByUserName);
	}

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}
}
