package com.security.practice.config.jwt;

import java.io.IOException;
import java.util.ArrayList;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.stereotype.Component;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.security.practice.config.CustomeAuthenticationManager;

import io.micrometer.common.util.StringUtils;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JWTAuthorizationFilter extends BasicAuthenticationFilter {
	
	public JWTAuthorizationFilter(CustomeAuthenticationManager authManager) {
		super(authManager);
	}
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		// Get authorization header and validate
        final String header = request.getHeader(SecurityConstants.HEADER_STRING);
        
        if (StringUtils.isEmpty(header) || !header.startsWith(SecurityConstants.TOKEN_PREFIX)) {
            chain.doFilter(request, response);
            return;
        }

        final String requestTokenHeader = request.getHeader(SecurityConstants.HEADER_STRING);
        
        UsernamePasswordAuthenticationToken authentication = null;
        if (requestTokenHeader != null && requestTokenHeader.startsWith(SecurityConstants.TOKEN_PREFIX)) {
            // parse the token.
            String user = JWT.require(Algorithm.HMAC512(SecurityConstants.SECRET.getBytes()))
                    .build()
                    .verify(requestTokenHeader.replace(SecurityConstants.TOKEN_PREFIX, ""))
                    .getSubject();

            if (user != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                // new arraylist means authorities
            	authentication =  new UsernamePasswordAuthenticationToken(user, null, new ArrayList<>());
            }

        }
		SecurityContextHolder.getContext().setAuthentication(authentication);
        chain.doFilter(request, response);
	}
	
}