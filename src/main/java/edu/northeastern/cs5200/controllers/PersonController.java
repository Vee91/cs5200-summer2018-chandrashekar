package edu.northeastern.cs5200.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import edu.northeastern.cs5200.controllers.hello.HelloObject;
import edu.northeastern.cs5200.dto.Person;
import edu.northeastern.cs5200.service.ProfileService;
import edu.northeastern.cs5200.util.ResponseResource;

@RestController
@RequestMapping("/api")
public class PersonController {
	
	@Autowired
	private ProfileService profileService;
	
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
    
    @RequestMapping(value = { "/profile" }, method = RequestMethod.GET)
   	@ResponseBody
   	public ResponseResource getProfile() {
   		return profileService.getProfile();
   	}
    
    @RequestMapping(value = { "/update/profile" }, method = RequestMethod.POST)
	@ResponseBody
	public ResponseResource updateProfile(@RequestBody Person person) {
		return profileService.updateProfile(person);
	}

}
