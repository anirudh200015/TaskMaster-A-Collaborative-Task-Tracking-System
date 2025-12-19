package com.project.Taskmaster.config;

import java.io.IOException;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.filter.OncePerRequestFilter;

import com.project.Taskmaster.Repository.UserRepository;
import com.project.Taskmaster.Util.TokenUtil;
import com.project.Taskmaster.entity.Users;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Configuration
public class AuthJWTAuthenticationFilter extends OncePerRequestFilter{
	@Autowired
	UserRepository userRepository;
	
		
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String authorizationHeader= request.getHeader("Authorization");
		
		if(authorizationHeader==null) {
			response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
			response.getWriter().write("Missing Auth header");
		}
		
		if(authorizationHeader!=null && authorizationHeader.startsWith("Bearer ")) {
			authorizationHeader=authorizationHeader.substring(7);
		}
		
		if(!TokenUtil.validateSignedKey(authorizationHeader)) {
			response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
			response.getWriter().write("Invalid user token");
		}
		
		String username= TokenUtil.getUsername(authorizationHeader);
		
		Users user= userRepository.findByUsername(username);
		
		UsernamePasswordAuthenticationToken authentication =
		        new UsernamePasswordAuthenticationToken(user, null, new ArrayList<>());
		
		SecurityContextHolder.getContext().setAuthentication(authentication);
		
		// move to next level (either controller or filter 2)
		filterChain.doFilter(request, response);
		

	}
	
	
	@Override
	protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
		// TODO Auto-generated method stub
		String path= request.getRequestURI();
		return path.contains("register")||path.contains("signin")||path.contains("verifyToken");
	}

}
