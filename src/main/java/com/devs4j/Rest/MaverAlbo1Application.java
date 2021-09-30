package com.devs4j.Rest;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.devs4j.Rest.Entity.Rol;
import com.devs4j.Rest.Entity.User;
import com.devs4j.Rest.Entity.UserInRol;
import com.devs4j.Rest.Repository.RolRepository;
import com.devs4j.Rest.Repository.UserInRolRepository;
import com.devs4j.Rest.Repository.UserRepository;
import com.github.javafaker.Faker;

@SpringBootApplication
public class MaverAlbo1Application implements ApplicationRunner{
	
	private static final Logger log = LoggerFactory.getLogger(MaverAlbo1Application.class);

	
	@Autowired
	private Faker faker;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private RolRepository rolRepository;
	
	@Autowired
	private UserInRolRepository userInRolRepository;

	public static void main(String[] args) {
		SpringApplication.run(MaverAlbo1Application.class, args);
	}

	@Override
	public void run(ApplicationArguments args) throws Exception {
		List<Rol> asList = Arrays.asList(new Rol("ADMIN"),new Rol("USER"),new Rol("INVIT"));
		asList.forEach(r -> rolRepository.save(r));
		
		for(int i=0;i<10;i++) {
			User user = new User();
			user.setUserName(faker.name().firstName());
			user.setPassword(faker.name().lastName());
			User  userCreate = userRepository.save(user);
			
			UserInRol userInRol = new UserInRol(user,asList.get((new Random().nextInt(3))));
			userInRolRepository.save(userInRol);
			log.info("Usuario:{} password:{} Rol:{}",userCreate.getUserName(),userCreate.getPassword(),userInRol.getRol().getName());
		}
	}
	
	
	
	

}
