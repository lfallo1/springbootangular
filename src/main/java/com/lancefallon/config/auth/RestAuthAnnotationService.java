package com.lancefallon.config.auth;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Component;

import com.lancefallon.config.ActiveUser;

@Component("authService")
public class RestAuthAnnotationService {
	
	public Boolean isSelf(UsernamePasswordAuthenticationToken token, String username){
		if(token == null){
			return false;
		}
		ActiveUser activeUser = (ActiveUser) token.getPrincipal();
		return username.equalsIgnoreCase(activeUser.getUsername());
	}
}
