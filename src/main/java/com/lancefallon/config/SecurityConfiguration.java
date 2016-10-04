package com.lancefallon.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import com.lancefallon.config.auth.RestAuthenticationEntryPoint;
import com.lancefallon.config.auth.RestAuthenticationFailureHandler;
import com.lancefallon.config.auth.RestAuthenticationProvider;
import com.lancefallon.config.auth.RestAuthenticationSuccessHandler;

@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
	@Autowired
	private RestAuthenticationEntryPoint authenticationEntryPoint;
	@Autowired
	private RestAuthenticationFailureHandler authenticationFailureHandler;
	@Autowired
	private RestAuthenticationSuccessHandler authenticationSuccessHandler;
	@Autowired
	private RestAuthenticationProvider authenticationProvider;

	@Autowired
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(this.authenticationProvider);
    }
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		http.authorizeRequests()
            .antMatchers("/auth/**", "/api/resource**").hasAnyRole("USER", "ADMIN")
            .antMatchers("/**").permitAll()
        .anyRequest().authenticated().and()
        .httpBasic().and()
        .logout().and()
        .csrf().disable();
		
		http.exceptionHandling().authenticationEntryPoint(authenticationEntryPoint);
		http.formLogin().successHandler(authenticationSuccessHandler);
		http.formLogin().failureHandler(authenticationFailureHandler);
	}
}
