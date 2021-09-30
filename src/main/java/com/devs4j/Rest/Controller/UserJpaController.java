package com.devs4j.Rest.Controller;

import java.util.List;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.devs4j.Rest.Entity.User;
import com.devs4j.Rest.Services.UserJpaService;

import io.micrometer.core.annotation.Timed;

@RestController
@RequestMapping("/userjpa")
public class UserJpaController {

	@Autowired
	private UserJpaService userJpaService;
	
	@GetMapping
	@Timed(value = "get.Users")
	public ResponseEntity<List<User>> getUsers(){
		return new ResponseEntity<List<User>>(userJpaService.getUsers(),HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<User> getUserById(@PathVariable("id") Integer id){
		return new ResponseEntity<User>(userJpaService.getUserById(id),HttpStatus.OK);
		
	}
	
	@GetMapping("userName/{userName}")
	public ResponseEntity<User> getUserByUserName(@PathVariable("userName") String usareName){
		return new ResponseEntity<User>(userJpaService.getUserByUserName(usareName),HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<User> getUserByUsareNameAndPassword(@RequestBody User user){
		return new ResponseEntity<User>(userJpaService.getUserByUserNameAndPassword(user.getUserName(), user.getPassword()),HttpStatus.OK);
	}
	
	@GetMapping("/pages")
	public ResponseEntity<Page<User>> getUsersPages(@RequestParam(required = false,value =  "page", defaultValue = "0") int page,@RequestParam(required = false,value =  "size",defaultValue = "5") int size){
		return new ResponseEntity<Page<User>>(userJpaService.getUsersPages(page, size),HttpStatus.OK);
	}
	
	@GetMapping("/userNames")
	public ResponseEntity<List<String>> getuserNames(){
		return new ResponseEntity<List<String>>(userJpaService.getUserNames(),HttpStatus.OK);
	}

	@GetMapping("/userNames/pages")
	public ResponseEntity<Page<String>> getuserNamesPages(@RequestParam(required = false,value =  "page", defaultValue = "0") int page,@RequestParam(required = false,value =  "size",defaultValue = "5") int size){
		return new ResponseEntity<Page<String>>(userJpaService.getUserNamesPages(page,size),HttpStatus.OK);
	} 	
	
	@DeleteMapping("/userName/{userName}")
	public ResponseEntity<Void> deleteUserByUserName(@PathVariable("userName") String userName){
		userJpaService.deleteUserByName(userName);
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		
	}
	
}
