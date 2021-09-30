package com.devs4j.Rest.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.devs4j.Rest.Entity.Perfil;

@Repository
public interface PerfilRepository extends JpaRepository<Perfil, Integer>{

	@Query("SELECT p FROM Perfil p WHERE p.user.id=?1 AND p.id=?2")
	Optional<Perfil> getPerfilByIidUserAndidPerfil(Integer idUser, Integer idPerfil);

}
