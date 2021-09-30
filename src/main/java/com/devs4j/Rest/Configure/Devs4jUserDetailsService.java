package com.devs4j.Rest.Configure;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.devs4j.Rest.Entity.User;
import com.devs4j.Rest.Entity.UserInRol;
import com.devs4j.Rest.Repository.UserInRolRepository;
import com.devs4j.Rest.Repository.UserRepository;

@Service
public class Devs4jUserDetailsService implements UserDetailsService{
	
	@Autowired
	private UserInRolRepository userInRolRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PasswordEncoderSpringSegurity passwordEncoder;
	

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<User> optional = userRepository.findByUserName(username);
		if (optional.isPresent()) {
				User user = optional.get();
				List<UserInRol> userInRoles =userInRolRepository.findByUser(user);
				String[] roles = userInRoles.stream().map(r->r.getRol().getName()).toArray(String[]::new);
				
				  return org.springframework.security.core.userdetails.User.withUsername(user.
						  getUserName()).password(passwordEncoder.encoder().encode(user.getPassword()))
				  .roles(roles).build();
				 
		} else {
				throw new UsernameNotFoundException("Username " + username +" not found");
		}

	}

}
