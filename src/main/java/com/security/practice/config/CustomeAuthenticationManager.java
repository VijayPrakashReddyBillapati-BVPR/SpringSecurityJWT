package com.security.practice.config;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.security.practice.model.UserInfo;

@Service
public class CustomeAuthenticationManager implements AuthenticationManager {

	@Autowired
	private UserDetailsServiceImpl userDetailsService;
	
	
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		String userEmail = authentication.getName();
		
		if(isValid(userEmail)) {
			try{
				UserInfo userDetails = userDetailsService.loadUserByUsername(authentication.getName());
				return new UsernamePasswordAuthenticationToken(userDetails.getUsername(),userDetails.getPassword(),userDetails.getAuthorities());
	        }catch (UsernameNotFoundException e){
	            throw new BadCredentialsException("Invalid Credentials");
	        }
		} else {
			throw new BadCredentialsException("Invalid Email address");
		}
	}

	private boolean isValid(String userEmail) {
		String regex = "^[A-Za-z0-9+_.-]+@(.+)$";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(userEmail); 
		return matcher.matches();
	}

}
