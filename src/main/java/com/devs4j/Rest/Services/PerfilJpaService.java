package com.devs4j.Rest.Services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.devs4j.Rest.Entity.Perfil;
import com.devs4j.Rest.Entity.User;
import com.devs4j.Rest.Repository.PerfilRepository;
import com.devs4j.Rest.Repository.UserRepository;

import net.bytebuddy.implementation.bytecode.Throw;

@Service
public class PerfilJpaService {
	
	@Autowired
	private PerfilRepository perfilRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	public List<Perfil> getPerfiles(){
		return perfilRepository.findAll();
	} 
	
	public Perfil createPerfil(Perfil perfil) {
		return perfilRepository.save(perfil);
	}
	
	public Perfil createPerfirWithtUser(Integer idUser,Perfil perfil) {
		Optional<User> findById = userRepository.findById(idUser);
		if(findById.isPresent()) {
			perfil.setUser(findById.get());
			return perfilRepository.save(perfil);
		}else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND,String.format("User %d not fount", idUser));
		}
		
	}
	
	public Perfil getPerfilByIdPerfil(Integer idUser, Integer idPerfil) {
		return perfilRepository.getPerfilByIidUserAndidPerfil(idUser,idPerfil).orElseThrow(
				() -> new ResponseStatusException(HttpStatus.NOT_FOUND,String.format("idUser %d and idPerfil %d dot´n exist", idUser,idPerfil))
				);
		
	}

	
	public Perfil update(Perfil perfil,Integer id) {
		Optional<Perfil> findById = perfilRepository.findById(id);
		if(findById.isPresent()) {
			return perfilRepository.save(perfil);
		}else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND,String.format("No existe el id %d",id));
		}
	}
	
	public void deletePerfil(Integer id) {
		Optional<Perfil> findById = perfilRepository.findById(id);
		if(findById.isPresent()) {
			perfilRepository.delete(findById.get());
		}else {
			throw new ResponseStatusException(HttpStatus.NO_CONTENT,String.format("No existe el id: %d", id));
		}
	}

	
}
