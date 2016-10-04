package com.lancefallon.web.controller;

import java.security.Principal;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.lancefallon.web.exception.CustomErrorMessage;
import com.lancefallon.web.exception.InvalidCredentialsException;

@RestController
public class UserPrincipalController {

	@RequestMapping(value="/user", method=RequestMethod.GET)
	public ResponseEntity<Principal> user(Principal user) throws InvalidCredentialsException {
		if(user == null){
			throw new InvalidCredentialsException(new CustomErrorMessage("notloggedin", "not logged in"));
		}
		return new ResponseEntity<Principal>(user, HttpStatus.OK);
	}
	
}
