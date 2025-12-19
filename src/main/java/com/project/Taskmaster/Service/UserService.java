package com.project.Taskmaster.Service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.project.Taskmaster.Repository.UserRepository;
import com.project.Taskmaster.Repository.VerificationRepository;
import com.project.Taskmaster.Util.TokenUtil;
import com.project.Taskmaster.entity.UserDTO;
import com.project.Taskmaster.entity.Users;
import com.project.Taskmaster.entity.VerificationToken;



@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private VerificationRepository verificationRepo;
	
	public Users register(UserDTO user) {
		// TODO Auto-generated method stub
		
		Users newUser= new Users();
		
		
		newUser.setusername(user.getusername());
		newUser.setPassword(passwordEncoder.encode(user.getPassword()));
		newUser.setisEnabled(false);
		newUser.setRole("ADMIN");
		
		userRepository.save(newUser);
		return newUser;
	}

	public void saveVerificationToken(String verificationToken, Users user) {
		// TODO Auto-generated method stub
		
		VerificationToken newToken = new VerificationToken();
		
		newToken.setToken(verificationToken);
		newToken.setUser(user);
		newToken.setExpireDate(new Date(System.currentTimeMillis()+1000*60*60*24));
		
		verificationRepo.save(newToken);
		
	}

	public boolean verifyUser(String verificationToken) {
		// TODO Auto-generated method stub
		
		VerificationToken token= verificationRepo.findByToken(verificationToken);
		if (token==null) {
			return false;
			
		}
		
		Users toBeVerifiedUser= token.getUser();
		
		Long registeredExpireTime=token.getExpireDate().getTime();
		
		if(registeredExpireTime < System.currentTimeMillis()) {
			
			userRepository.delete(toBeVerifiedUser);
			verificationRepo.delete(token);
			return false;
		}
				
		
		return true;
		
	}
	
	public void EnableUser(String verificationToken ) {
		
		VerificationToken token= verificationRepo.findByToken(verificationToken);
		
		Users toBeVerifiedUser= token.getUser();
		
		
		toBeVerifiedUser.setisEnabled(true);
		
		verificationRepo.delete(token);
		userRepository.save(toBeVerifiedUser);
		
	}

	public String signIn(String username, String password) {
		// TODO Auto-generated method stub
		
		Users toBeSignedinUser= userRepository.findByUsername(username);
		
		if(toBeSignedinUser==null) {
			return "User not found";
		}
		
		if(!toBeSignedinUser.getisEnabled()) {
			return "User not enabled active, pls enable it . ";
		}
		
		String fetchedPassword= toBeSignedinUser.getPassword();
		boolean isMatch= passwordEncoder.matches(password, fetchedPassword);
		
		if(!isMatch) {
			return "Incorrect password";
		}
		
		return TokenUtil.generateToken(toBeSignedinUser,toBeSignedinUser.getRole());
		
	}

	
	

	
	
}
