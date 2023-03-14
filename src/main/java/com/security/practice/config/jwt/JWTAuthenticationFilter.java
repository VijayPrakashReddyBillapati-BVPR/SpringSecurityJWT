package com.security.practice.config.jwt;

import java.io.IOException;
import java.util.Date;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.security.practice.config.CustomeAuthenticationManager;
import com.security.practice.model.User;
import com.security.practice.model.UserInfo;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
	
	
	private CustomeAuthenticationManager authenticationManager;

    public JWTAuthenticationFilter(CustomeAuthenticationManager authenticationManager) {
    	this.authenticationManager = authenticationManager;
        setFilterProcessesUrl("/api/services/user/login"); 
    }
    

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException {
		try {
			UserInfo credentials = new ObjectMapper().readValue(request.getInputStream(), UserInfo.class);
			return authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(credentials.getUsername(), credentials.getPassword(), credentials.getAuthorities()));
		} catch (IOException e) {
			 throw new RuntimeException(e);
		}
	}

	@Override
	protected void successfulAuthentication( HttpServletRequest request, HttpServletResponse response, FilterChain chain,
			Authentication authResult) throws IOException, ServletException {
		String token = JWT.create()
                .withSubject(((User) authResult.getPrincipal()).getUsername())
                .withExpiresAt(new Date(System.currentTimeMillis() + SecurityConstants.EXPIRATION_TIME))
                .sign(Algorithm.HMAC512(SecurityConstants.SECRET.getBytes()));

        String body = ((User) authResult.getPrincipal()).getUsername() + " " + token;

        ((ServletResponse) request).getWriter().write(body);
        response.getWriter().flush();
	}

}
