package com.auth.jwt.Impl;

import java.security.Key;
import java.sql.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
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

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Service
public class userServiceImpl implements userService {
	
	private String secretKey=null;
	
	@Autowired
	private userRepository userRepo;
	
	 @Autowired
	 private PasswordEncoder psswdEncoder;
	 
	 @Autowired
	 @Lazy
	 private AuthenticationManager authManager;

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

	@Override
	public String jwtToken(createUserDto dto) {
		// TODO Auto-generated method stub		 
		//var valid= userRepo.findByUserName(user.getUserName());
		Authentication auth=authManager.authenticate(new UsernamePasswordAuthenticationToken(dto.getUserName(), dto.getPassword()));
		if(auth.isAuthenticated()) {
			return generateToken(dto);
		}else {
			return "failed";
		}
	}

	@Override
	public String generateToken(createUserDto dto) {
		// TODO Auto-generated method stub
		Map<String, Object> payload= new HashMap<>();
		return Jwts.builder().claims().add(payload).subject(dto.getUserName()).issuer("Sai").issuedAt(new Date(System.currentTimeMillis()))
				.expiration(new Date(System.currentTimeMillis()+60*10*1000)).and().signWith(generateKey()).compact();
		//return null;
	}
    
	private SecretKey generateKey() {
		// TODO Auto-generated method stub
		byte[] decode = Decoders.BASE64.decode(getSecretKey());
		return Keys.hmacShaKeyFor(decode);
	}

	public String getSecretKey() {
		return secretKey="kco4Q2rZT2fPAJWZrdG9nmWXgIgKq0X2aVcPeyKwUHh";
	}


	}
