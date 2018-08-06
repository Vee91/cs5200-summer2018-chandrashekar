package edu.northeastern.cs5200.controllers;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.northeastern.cs5200.controllers.hello.HelloObject;

@RestController
@RequestMapping("/api")
public class PersonController {
	
	@GetMapping("/user")
    public HelloObject helloUser(){
		HelloObject obj = new HelloObject("Hello Vikas Chandrashekar User!");
		return obj;
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @GetMapping("/admin")
    public HelloObject securedUser(){
    	HelloObject obj = new HelloObject("Hello Vikas Chandrashekar ADmin!");
		return obj;
    }

}
