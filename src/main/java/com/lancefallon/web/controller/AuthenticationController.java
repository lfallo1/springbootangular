package com.lancefallon.web.controller;

import java.security.Principal;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class AuthenticationController {

	@RequestMapping(method=RequestMethod.GET)
	public Principal getUser(Principal user){
		return user;
	}
	
}