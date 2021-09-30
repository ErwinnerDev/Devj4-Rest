package com.devs4j.Rest.Controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.devs4j.Rest.Entity.Rol;
import com.devs4j.Rest.Entity.User;
import com.devs4j.Rest.Services.RolJpaService;

@RestController
@RequestMapping("/roles")
public class RolJpaController {

	@Autowired
	private RolJpaService rolService;
	
	@Autowired
	private RolJpaService RolJpaService;
	
	
	private static final Logger log = LoggerFactory.getLogger(RolJpaController.class);


	@GetMapping
	public ResponseEntity<List<Rol>> getRoles() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		log.info("Name: {}",authentication.getName());
		log.info("Principal: {}",authentication.getPrincipal());
		log.info("Credentials: {} ",authentication.getCredentials());
		log.info("Role: {}",authentication.getAuthorities().toString());
		return new ResponseEntity<List<Rol>>(rolService.getRoles(), HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<Rol> createRol(@RequestBody Rol rol){
		return new ResponseEntity<Rol>(rolService.createRol(rol),HttpStatus.CREATED);	
	}
	
	@PutMapping(value="/{id}")
	public ResponseEntity<Rol> updateRol(@RequestBody Rol rol,@PathVariable(name = "id") Integer id){
		return new ResponseEntity<Rol>(rolService.updateRol(rol, id),HttpStatus.OK);	
	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> deleteRol(@PathVariable(name = "id")  Integer id){
		rolService.deleteRol(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	@GetMapping("/{rolName}/users")	
	public ResponseEntity<List<User>> getUsersByNameRol(@PathVariable("rolName") String rolName){
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		log.info("Name: {}",authentication.getName());
		log.info("Principal: {}",authentication.getPrincipal());
		log.info("Credentials: {} ",authentication.getCredentials());
		log.info("Role: {}",authentication.getAuthorities().toString());
		return new ResponseEntity<List<User>>(RolJpaService.getUsersByNameRol(rolName),HttpStatus.OK);
	}
	
	
}
