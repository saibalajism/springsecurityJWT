package com.auth.jwt.Impl;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.auth.jwt.config.webSecurityConfig;
import com.auth.jwt.dto.createUserDto;
import com.auth.jwt.entity.userEntity;
import com.auth.jwt.repository.userRepository;
import com.auth.jwt.service.userService;

@Service
public class userServiceImpl implements userService {
	
	@Autowired
	private userRepository userRepo;
	
	 @Autowired
	 private PasswordEncoder psswdEncoder;

	@Override
	public userEntity createUser(createUserDto dto) {
		userEntity existingUser = userRepo.findByUserName(dto.getUserName());
	    if (existingUser != null) {
	        throw new RuntimeException("Username is already taken!"); 
	    }
		
		userEntity userDetails = new userEntity();
		userDetails.setUserName(dto.getUserName());
		//userDetails.setPassword(dto.getPassword());
		userDetails.setPassword(psswdEncoder.encode(dto.getPassword()));
		userDetails.setUserId(dto.getUserId());
		return userRepo.save(userDetails);
	}

	@Override
	public userEntity userLogin(createUserDto dto) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		userEntity userDetails= userRepo.findByUserName(dto.getUserName());
		
		 
		return userDetails;
	}

	@Override
	public UserDetails loadUserByUsername(String Username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		userEntity user=userRepo.findByUserName(Username);
		if(Objects.isNull(user)) {
			System.out.println("User not found 1.");
		}
		return new customUserDetailsImpl(user);
	}



	}
