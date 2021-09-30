package com.devs4j.Rest.Services;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.devs4j.Rest.Entity.Address;
import com.devs4j.Rest.Entity.Perfil;
import com.devs4j.Rest.Entity.User;
import com.devs4j.Rest.Repository.AddressRepository;
import com.devs4j.Rest.Repository.PerfilRepository;

@Service
public class AddressJpaService {
	
	@Autowired
	private AddressRepository addressRepository;
	

	@Autowired
	private PerfilRepository perfilRepository;
	

	public List<Address> getAddressByiduserAndidPerfil(Integer idUser, Integer idPerfil) {
		return addressRepository.findAddressesByUsetIdAndPerfilid(idUser,idPerfil).orElseThrow(() ->
				new ResponseStatusException(HttpStatus.NOT_FOUND,""));

	}

	public Address createAddress(Integer idUser, Integer idProfile, Address address) {
		Optional<Perfil> perfilByIidUserAndidPerfil = perfilRepository.getPerfilByIidUserAndidPerfil(idUser,idProfile);
		if(perfilByIidUserAndidPerfil.isPresent()) {
			address.setPerfil(perfilByIidUserAndidPerfil.get());
			return addressRepository.save(address);
		}else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND,String.format("idUser %d and idPerfil %d dot´n exist", idUser,idProfile));
		}
	}

}
