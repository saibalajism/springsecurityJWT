package com.auth.jwt.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.auth.jwt.dto.productDto;
import com.auth.jwt.entity.productEntity;
import com.auth.jwt.repository.productRepository;
import com.auth.jwt.service.productService;

@Service
public class productServiceImpl implements productService{

	@Autowired
	private productRepository productRepo;
	
	@Override
	public List<productEntity> getProduct() {
		
		return productRepo.findAll();
	}

	@Override
	public productEntity createProduct(productDto dto) {
		productEntity details = new productEntity();
		details.setProductId(dto.getProductId());
		details.setPrice(dto.getPrice());
		details.setProductName(dto.getProductName());
		return productRepo.save(details);
	}
	

}
