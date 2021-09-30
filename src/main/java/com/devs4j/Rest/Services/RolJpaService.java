package com.devs4j.Rest.Services;

import java.util.List;
import java.util.Optional;

import javax.annotation.security.RolesAllowed;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.devs4j.Rest.Entity.Rol;
import com.devs4j.Rest.Entity.User;
import com.devs4j.Rest.Repository.RolRepository;
import com.devs4j.Rest.Repository.UserInRolRepository;

@Service
public class RolJpaService {

	@Autowired
	private RolRepository rolRepository;
	
	@Autowired
	private UserInRolRepository userInRolRepository;

	
	private static final Logger log = LoggerFactory.getLogger(RolJpaService.class);


	public List<Rol> getRoles() {
		return rolRepository.findAll();
	}
	
	public Rol createRol(Rol rol) {
		return rolRepository.save(rol);
	}
	
	public Rol updateRol(Rol rol, Integer id) {
		Optional<Rol> findById = rolRepository.findById(id);
		if(findById.isPresent()) {
			return rolRepository.save(rol);
		}else {
			throw new ResponseStatusException(HttpStatus.NO_CONTENT,String.format("Role id %d does't exists", id));
		}
	}
	
	public void deleteRol(Integer id) {
		Optional<Rol> findById = rolRepository.findById(id);
		if(findById.isPresent()) {
			rolRepository.deleteById(id);
		}else {
			throw new  ResponseStatusException(HttpStatus.NO_CONTENT,"");
		}
	}
	
	//todos estos ejemplos son por @EnableGlobalMethodSecurity(*configuracion*) en SecurityJavaConfig.java y  , se coloca siempre ROL_cualquierRolQuetengamos
	//@Secured("ROLE_USER") //configuracion: securedEnabled = true, en caso se usar SpringSecurity
	//@RolesAllowed("ROLE_USER")//configuracion: jsr250Enabled = true, en caso de usar solo  JEE
	@PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')") //quienes pueden ejecutar el método
	@PostAuthorize("hasRole('ROLE_ADMIN')") // quienes pueden tener una respuesta dle Método
	public List<User> getUsersByNameRol(String nameRol){
		log.info("Pudo entrara y se ejecutará el método");
		 Optional<List<User>> usersByNameRol = userInRolRepository.findUsersByRolName(nameRol);
			 if(usersByNameRol.isPresent()) {
				 return usersByNameRol.get();
			 }else {
				 throw new ResponseStatusException(HttpStatus.NOT_FOUND,"");
			 }
		 }


}
