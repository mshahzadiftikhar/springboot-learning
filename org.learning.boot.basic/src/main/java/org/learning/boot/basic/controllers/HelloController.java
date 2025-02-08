package org.learning.boot.basic.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController  // Accept REST requests
public class HelloController {

	@GetMapping("/hello")
	public String welcome() {
		return "Welcome from HelloController";
	}
	
	@GetMapping("/bye")
	public String bye() {
		return "Bye from HelloController";
	}
}
