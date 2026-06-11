package com.auth.jwt.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import com.auth.jwt.service.userService;

@Configuration
@EnableWebSecurity
public class webSecurityConfig {


	@Bean
	public SecurityFilterChain securityFilter(HttpSecurity httpsecurity) throws Exception {
		httpsecurity.csrf(s -> s.disable()).authorizeHttpRequests(request -> request.requestMatchers("register","login").permitAll()
				.anyRequest().authenticated()).httpBasic(Customizer.withDefaults());
		return httpsecurity.build();
	}
	
	@Bean
	public AuthenticationProvider authProvider(userService userDetailsService,PasswordEncoder passwordEncoder) {
		DaoAuthenticationProvider provider = new DaoAuthenticationProvider(userDetailsService);
		provider.setPasswordEncoder(passwordEncoder); 
		return provider;
	}
	
	@Bean
	public PasswordEncoder  passwordEncoder() {
	    // This is secure, modern, and generates zero deprecation warnings
	    return new BCryptPasswordEncoder(14); 
	}

}
