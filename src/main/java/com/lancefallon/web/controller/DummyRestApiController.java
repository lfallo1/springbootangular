package com.lancefallon.web.controller;

import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.lancefallon.config.ActiveUser;

@RestController
@RequestMapping("/auth")
public class DummyRestApiController {

	@RequestMapping(value="/v1/users", method=RequestMethod.GET)
	public ResponseEntity<DummyDto> getUsers(){
		DummyDto dummy = new DummyDto();
		dummy.name = "dummy";
		dummy.code = UUID.randomUUID();
		return new ResponseEntity<>(dummy, HttpStatus.OK);
	}
	
	@RequestMapping(value="/v1/users/{username}", method=RequestMethod.GET)
	@PreAuthorize("hasRole('ROLE_USER') and @authService.isSelf(#principal, #username)")
	public ResponseEntity<DummyDto> testGET(@PathVariable("username") String username, UsernamePasswordAuthenticationToken principal) {
		DummyDto dummy = new DummyDto();
		dummy.setName("Why hello, " + ((ActiveUser)principal.getPrincipal()).getUsername());
		dummy.setCode(UUID.randomUUID());
		return new ResponseEntity<DummyDto>(dummy, HttpStatus.OK);
	}
	
	public class DummyDto{
		private String name;
		private UUID code;
		
		public DummyDto(){}
		
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public UUID getCode() {
			return code;
		}
		public void setCode(UUID code) {
			this.code = code;
		}
		
		
	}
	
}
