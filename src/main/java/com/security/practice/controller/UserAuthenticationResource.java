package com.security.practice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.security.practice.config.jwt.JwtTokenUtil;
import com.security.practice.config.jwt.SecurityConstants;

@RestController
@RequestMapping("/api/services/user")
public class UserAuthenticationResource {
	
	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

//	@GetMapping("/login")
//	public ResponseEntity<String> UserLogin(@RequestParam String email,@RequestParam String password) {
	@GetMapping("/login/email/{email}/password/{password}")
	public ResponseEntity<String> UserLogin(@PathVariable String email,@PathVariable String password) {
		 try {
	            Authentication authenticate = authenticationManager.authenticate(
	                    new UsernamePasswordAuthenticationToken(
	                    		email, password
	                    )
	                );
	            return ResponseEntity.ok()
	            	.header(SecurityConstants.HEADER_STRING, jwtTokenUtil.generateToken(authenticate.getName()))
	                .body("<h1> Hi User Welcome :"+jwtTokenUtil.generateToken(authenticate.getName())+"</h1>");
	            
	        } catch (BadCredentialsException ex) {
	            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
	        }
	}
}
