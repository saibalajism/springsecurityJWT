package com.auth.jwt.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.auth.jwt.dto.productDto;
import com.auth.jwt.entity.productEntity;
import com.auth.jwt.service.productService;

@RestController
@RequestMapping("/product")
public class productController {
	
	@Autowired
	private productService service;
	
	@GetMapping
	public List<productEntity> getProducts() {
		return service.getProduct();
	}
	
	@PostMapping
	public productEntity createProduct(@RequestBody productDto dto) {
		return service.createProduct(dto);		
	}

}
