package com.project.Taskmaster.Util;

import java.util.Date;

import com.project.Taskmaster.entity.Users;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

public class TokenUtil {

public static String generateToken(Users user, String role) {
		
		
		return Jwts.builder().subject(user.getUsername()).issuedAt(new Date())
				.setExpiration(new Date(System.currentTimeMillis()+8*60*1000))
				.claim("roles","ROLE_"+user.getRole())
				.signWith(io.jsonwebtoken.SignatureAlgorithm.HS256,"secretKeyforTaskManagerApplicationprojectbymeandItisTemporarykey")
				.compact();
				
		
	}

public static boolean validateSignedKey(String authorizationHeader) {
	// TODO Auto-generated method stub
	
	Claims body= Jwts.parser()
			.setSigningKey("secretKeyforTaskManagerApplicationprojectbymeandItisTemporarykey")
			.build()
			.parseClaimsJws(authorizationHeader)
			.getBody();
			
	return true;
}

public static String getUsername(String authorizationHeader) {
	// TODO Auto-generated method stub
			
	String username= Jwts.parser()
			.setSigningKey("secretKeyforTaskManagerApplicationprojectbymeandItisTemporarykey")
			.build()
			.parseClaimsJws(authorizationHeader)
			.getBody()
			.getSubject();		
	System.out.println("username is : " + username);
	return username;
}

}
