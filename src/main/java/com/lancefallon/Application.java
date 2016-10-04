package com.lancefallon;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.lancefallon.config.SecurityConfiguration;

@SpringBootApplication
public class Application extends WebMvcConfigurerAdapter {

	public static void main(String[] args) {
		SpringApplication.run(new Class[]{Application.class, SecurityConfiguration.class}, args);
	}
	
	@Override
	public void configurePathMatch(PathMatchConfigurer configurer) {
	    super.configurePathMatch(configurer);
	    configurer.setUseSuffixPatternMatch(false);
	}
}
