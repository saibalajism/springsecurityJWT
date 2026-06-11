package com.auth.jwt.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import com.auth.jwt.entity.userEntity;


public interface userRepository extends JpaRepository<userEntity, Integer>{
      userEntity findByUserName(String userName);

}