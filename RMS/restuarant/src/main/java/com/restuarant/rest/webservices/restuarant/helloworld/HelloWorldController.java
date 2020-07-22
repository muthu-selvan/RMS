package com.restuarant.rest.webservices.restuarant.helloworld;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {
	
	@GetMapping(path="/hello-world")
	public String getMessage() {
		return "Hello World";
	}
}
