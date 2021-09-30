package com.devs4j.Rest.Controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.devs4j.Rest.Entity.Perfil;
import com.devs4j.Rest.Services.PerfilJpaService;

@RestController
@RequestMapping("perfil")
public class PerfilJpaController {
	
	
	private static final Logger log = LoggerFactory.getLogger(PerfilJpaController.class);

	
	@Autowired
	private PerfilJpaService perfilService;
	
	@GetMapping
	public ResponseEntity<List<Perfil>> getPerfiles(){
		return new ResponseEntity<List<Perfil>>(perfilService.getPerfiles(),HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<Perfil> createPerfil(@RequestBody Perfil perfil){
		return new ResponseEntity<Perfil>(perfilService.createPerfil(perfil),HttpStatus.CREATED);
	}
	
	@PostMapping("/users/{idUser}/perfil")
	public ResponseEntity<Perfil> createPerfilWithUser(@PathVariable("idUser")Integer idUser, @RequestBody Perfil perfil){
		return new ResponseEntity<Perfil>(perfilService.createPerfirWithtUser(idUser, perfil),HttpStatus.CREATED);
	}
	
	@GetMapping("/users/{idUser}/perfil/{idPerfil}")
	public ResponseEntity<Perfil> getPerfilByIdPerfil(@PathVariable("idUser") Integer idUser,@PathVariable("idPerfil") Integer idPerfil){
		return new ResponseEntity<Perfil>(perfilService.getPerfilByIdPerfil(idUser,idPerfil),HttpStatus.OK);
	}
	
	
	@PutMapping("/{id}")
	public ResponseEntity<Perfil> updatePerfil(@RequestBody Perfil perfil,@PathVariable Integer id){
		return new ResponseEntity<Perfil>(perfilService.update(perfil, id),HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deletePerfil(@PathVariable Integer id) {
		perfilService.deletePerfil(id);
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}
	

}
