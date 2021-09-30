package com.devs4j.Rest.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.devs4j.Rest.Entity.Address;

@Repository
public interface AddressRepository extends JpaRepository<Address,Integer>{

	@Query("SELECT d FROM Address d WHERE d.perfil.id=?2 AND  d.perfil.user.id=?1")
	Optional<List<Address>> findAddressesByUsetIdAndPerfilid(Integer idUser, Integer idPerfil);

}
