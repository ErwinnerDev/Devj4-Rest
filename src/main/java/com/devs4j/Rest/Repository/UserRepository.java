package com.devs4j.Rest.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.devs4j.Rest.Entity.User;
@Repository
public interface UserRepository extends JpaRepository<User, Integer>{
	
	public Optional<User>  findByUserName(String userName);
	
	public Optional<User> findByUserNameAndPassword(String userName,String password);
	
	//Esto No es SQL se llama JPQL, y toma el nombre de la clase
	@Query("SELECT u.userName FROM User u")
	public List<String> findUserNames();
	
	@Query("SELECT u.userName FROM User u")
	public Page<String> findUserNamesPages(Pageable pageable);
	

}
