package edu.northeastern.cs5200.controllers;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.northeastern.cs5200.controllers.hello.HelloObject;
import edu.northeastern.cs5200.util.ResponseResource;

@RestController
@RequestMapping("/api")
public class PersonController {
	
	@GetMapping("/user")
    public HelloObject helloUser(){
		HelloObject obj = new HelloObject("Hello Vikas Chandrashekar User!");
		return obj;
    }

    @GetMapping("/admin")
    public ResponseResource securedUser(){
    	ResponseResource out = new ResponseResource();
    	out.setCode("200");
    	out.setMessage("This is default admin log in page");
    	return out;
    }

}
