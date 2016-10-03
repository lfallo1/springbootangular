package com.lancefallon.config;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;

@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
	@Override
	protected void configure(HttpSecurity http) throws Exception { // @formatter:off
		http
	      .httpBasic().and()
	      .authorizeRequests()
	        .antMatchers("/bower/**", "/js/**", "/index.html", "/templates/home.html", "/templates/login.html", "/").permitAll().anyRequest()
	        .authenticated().and()
	      .csrf()
	        .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse());
	} //@formatter:on
}
