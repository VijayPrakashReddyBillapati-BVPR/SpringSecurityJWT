package com.security.practice.config.jwt;

import java.util.Date;

import org.springframework.stereotype.Component;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

@Component
public class JwtTokenUtil {

	public String generateToken(String userName) {
		 return SecurityConstants.TOKEN_PREFIX + " " + JWT.create()
        .withSubject(userName)
        .withExpiresAt(new Date(System.currentTimeMillis() + SecurityConstants.EXPIRATION_TIME))
        .sign(Algorithm.HMAC512(SecurityConstants.SECRET.getBytes()));
	}
}
