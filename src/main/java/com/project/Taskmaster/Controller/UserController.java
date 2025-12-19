package com.project.Taskmaster.Controller;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.project.Taskmaster.Service.UserService;
import com.project.Taskmaster.entity.UserDTO;
import com.project.Taskmaster.entity.Users;



@RestController
public class UserController {
	
	@Autowired
	private UserService userService;

	@PostMapping("/register")
	private String CreateNewUser(@RequestBody UserDTO newUser) {
		
		Users CreatedUser=userService.register(newUser);
		String verificationToken=UUID.randomUUID().toString();
		String verificationUrl="http://localhost:8080/verifyToken?verificationToken="+verificationToken;
		System.out.println("Please verify your registration by clicking on the following link: "+verificationUrl);
		
		userService.saveVerificationToken(verificationToken,CreatedUser);
		
		return "new user is created";
	
	}
	
	@PostMapping("/verifyToken")
	public String verifyToken(@RequestParam String verificationToken) {
		
		boolean isVerified = userService.verifyUser(verificationToken);
		
		if(!isVerified) {
			
			
			return "Token validation failed , try again! ";
		}
		
		
		userService.EnableUser(verificationToken);
		return "User verified , proceed to login page ";
		
	}
	
	@PostMapping("/signin")
	public String signin(@RequestParam String username, @RequestParam String password) {
		
		String JwtToken=userService.signIn(username, password);
		
		return JwtToken;
		
	}
	
}

