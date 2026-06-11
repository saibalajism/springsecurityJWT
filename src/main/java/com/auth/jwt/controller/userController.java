package com.auth.jwt.controller;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.auth.jwt.dto.createUserDto;
import com.auth.jwt.entity.userEntity;
import com.auth.jwt.repository.userRepository;
import com.auth.jwt.service.userService;

@RestController
public class userController {
	
	@Autowired
	private userRepository userRepo;
	
	@Autowired
	private userService service;
		
	@PostMapping("/register")
	public userEntity createUser(@RequestBody createUserDto user) {
		return service.createUser(user);
	}
	
	@PostMapping("/login")
	public String userLogin(@RequestBody createUserDto user) {
		var valid= userRepo.findByUserName(user.getUserName());
		if(!Objects.isNull(valid)) {
			return "Success";
		}else {
			return "failed";
		}
	}

}
