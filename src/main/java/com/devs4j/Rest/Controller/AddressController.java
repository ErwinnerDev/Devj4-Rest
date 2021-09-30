package com.devs4j.Rest.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.devs4j.Rest.Entity.Address;
import com.devs4j.Rest.Services.AddressJpaService;
@RestController
@RequestMapping("/users/{idUser}/perfil/{idProfile}/addresses")
public class AddressController {
	
	@Autowired
	private AddressJpaService addressJpaService;
	
	@GetMapping
	public ResponseEntity<List<Address>> getAddressByiduserAndidPerfil(@PathVariable("idUser") Integer idUser
																		,@PathVariable("idProfile") Integer idPerfil){
		return new ResponseEntity<List<Address>>(addressJpaService.getAddressByiduserAndidPerfil(idUser,idPerfil),HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<Address> createaddress(@PathVariable("idUser") Integer idUser
												,@PathVariable("idProfile") Integer idProfile
												,@RequestBody Address address){
		return new ResponseEntity<Address>(addressJpaService.createAddress(idUser,idProfile,address),HttpStatus.CREATED);
		
	}
	

}
