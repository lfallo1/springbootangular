package com.lancefallon.config.interceptor;

import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.lancefallon.web.exception.CustomErrorMessage;
import com.lancefallon.web.exception.InvalidCredentialsException;

/**
 * Interceptor designed to allow only logged in users to pass through
 * @author lfallon
 *
 */
@Service
public class ApiInterceptor implements HandlerInterceptor {

	@Override
	public void afterCompletion(HttpServletRequest req, HttpServletResponse res, Object handler, Exception ex)
			throws Exception {
		//stub
	}

	@Override
	public void postHandle(HttpServletRequest req, HttpServletResponse res, Object handler, ModelAndView ex)
			throws Exception {
		// stub
	}

	@Override
	public boolean preHandle(HttpServletRequest req, HttpServletResponse res, Object handler) throws InvalidCredentialsException {

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		//if context null, throw error
		if(auth == null || auth.getAuthorities()
				.stream()
				.filter(a->a.getAuthority().contains("USER") || a.getAuthority().contains("ADMIN"))
				.collect(Collectors.toList())
				.size() == 0){
			throw new InvalidCredentialsException(new CustomErrorMessage("notloggedin", "You are not authorized to view this page"));
		}
		

		return true;
	}

}
