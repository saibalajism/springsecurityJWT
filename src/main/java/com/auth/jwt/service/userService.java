package com.auth.jwt.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

import com.auth.jwt.dto.createUserDto;
import com.auth.jwt.entity.userEntity;


@Component
public interface userService extends UserDetailsService {
	
	userEntity createUser(createUserDto dto);
	
	userEntity userLogin(createUserDto dto);
	
	UserDetails loadUserByUsername(String Username);

}
