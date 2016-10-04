package com.lancefallon.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.lancefallon.config.interceptor.ApiInterceptor;

@Configuration
public class MvcConfiguration extends WebMvcConfigurerAdapter  {

	@Autowired
	private ApiInterceptor apiInterceptor;
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(apiInterceptor).addPathPatterns("/intercept**");
		super.addInterceptors(registry);
	}

}
