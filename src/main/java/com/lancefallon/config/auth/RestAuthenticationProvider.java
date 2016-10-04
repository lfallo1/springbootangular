package com.lancefallon.config.auth;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import com.lancefallon.config.ActiveUser;

@Component
public class RestAuthenticationProvider implements AuthenticationProvider {

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName();
        String password = authentication.getCredentials().toString();
        
    	if(!username.equals("user") || !password.equals("user")){
    		throw new BadCredentialsException("invalid credentials");
    	}
    	ActiveUser user = new ActiveUser();
    	user.setUsername(username);
    	List<GrantedAuthority> grantedAuths = new ArrayList<>();
    	
    	//TODO get roles from database
        grantedAuths.add(new SimpleGrantedAuthority("ROLE_USER"));
        
        Authentication auth = new UsernamePasswordAuthenticationToken(user, password, grantedAuths);
        return auth;
    }
 
    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
