package com.auth.jwt.Impl;

import java.util.Collection;
import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.auth.jwt.entity.userEntity;
import com.auth.jwt.repository.userRepository;
import com.auth.jwt.service.customUserDetails;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class customUserDetailsImpl implements UserDetails{
	
	@Autowired
	private userEntity user;


	/*private userEntity user;

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return Collections.singleton(new SimpleGrantedAuthority("USER"));
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return user.getPassword();
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return user.getUserName();
	}*/


	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return Collections.singleton(new SimpleGrantedAuthority("USER"));
	}


	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		if (this.user == null) {
	        return null;
	    }
		return user.getPassword();
	    
	}


	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		if (this.user == null) {
	        return null;
	    }
		return user.getUserName();
	}
}
