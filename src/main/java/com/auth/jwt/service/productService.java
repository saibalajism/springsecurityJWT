package com.auth.jwt.service;

import java.util.List;

import com.auth.jwt.dto.productDto;
import com.auth.jwt.entity.productEntity;

public interface productService {
	List<productEntity> getProduct();
	productEntity createProduct(productDto dto);

}
