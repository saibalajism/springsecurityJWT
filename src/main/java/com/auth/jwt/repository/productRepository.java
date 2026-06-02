package com.auth.jwt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.auth.jwt.entity.productEntity;

@Repository
public interface productRepository extends JpaRepository<productEntity, Integer>{

}
