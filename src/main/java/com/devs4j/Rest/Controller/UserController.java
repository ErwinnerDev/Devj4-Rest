package com.devs4j.Rest.Controller;

import java.util.List;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.devs4j.Rest.Model.User;
import com.devs4j.Rest.Services.UserServices;

@RestController
@RequestMapping(value={"users"})
//Definición de mi recurso
public class UserController {
	
	@Autowired
	private UserServices userServices;
	
	//Metodo HTTP  + Recurso  ->  Handler methods
	@GetMapping()
	public ResponseEntity<List<User>> getUsers(){
		return new ResponseEntity<List<User>>(userServices.getUsers(),HttpStatus.OK);
	}
	
	@GetMapping(value="/{userName}")// para poder pasar un parametro en la URL
	public ResponseEntity<User> getUserByUserName(@PathVariable("userName") String userName){ //EL PathVariabla es para settear el valor pasado en la URL a una variable 
		return new ResponseEntity<User>(userServices.getUserByUserName(userName),HttpStatus.OK);
	}
	
	@GetMapping(value = "/requestParam")
	public ResponseEntity<List<User>> getUsersByUserNameRequestParam(@RequestParam(value = "userName",required = false) String userName){
		return new ResponseEntity<List<User>>(userServices.getUsersByUserNameRequestParam(userName),HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<User> createUser(@RequestBody User user){
		return new ResponseEntity<User>(userServices.createUser(user),HttpStatus.CREATED);
	}
	
	@PutMapping(value="/{userName}")
	public ResponseEntity<User> updateUser( @RequestBody User user,@PathVariable("userName") String userName ){
		return new ResponseEntity<User>(userServices.updateUser(userName,user), HttpStatus.OK);
	}
	
	@DeleteMapping(value="/{userName}")
	public ResponseEntity<Void> deleteUser(@PathVariable("userName") String userName){


		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	

}
