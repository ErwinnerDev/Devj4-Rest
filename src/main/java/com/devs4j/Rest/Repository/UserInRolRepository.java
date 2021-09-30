package com.devs4j.Rest.Repository;


import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.devs4j.Rest.Entity.User;
import com.devs4j.Rest.Entity.UserInRol;

@Repository
public interface UserInRolRepository extends CrudRepository<UserInRol,Integer>{
	
	@Query("SELECT u.user FROM UserInRol u WHERE u.rol.name=?1")
	Optional<List<User>> findUsersByRolName(String nameRol);
	
	List<UserInRol> findByUser(User user);

	

}
