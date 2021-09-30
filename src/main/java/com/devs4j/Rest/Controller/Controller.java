package com.devs4j.Rest.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hello")
public class Controller {
	
	@GetMapping
	public String hello() {
		return "Hola Mundo";
	}  

}
