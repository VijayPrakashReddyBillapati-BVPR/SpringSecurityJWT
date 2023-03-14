package com.security.practice.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.security.practice.config.jwt.JWTAuthenticationFilter;
import com.security.practice.config.jwt.JWTAuthorizationFilter;

@EnableWebSecurity
@Configuration
public class SecurityConfig {

//	@Autowired
//    private CustomAuthenticationProvider authenticationProvider;
	
	@Autowired
	private CustomeAuthenticationManager authenticationManager;
	
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		
		return http.cors().and().csrf().disable()
		.authorizeHttpRequests()
		.requestMatchers("/emps/**").hasRole("ADMIN")
		.and()
		.authorizeHttpRequests()
		.requestMatchers("/users").authenticated()
		.and()
		.authorizeHttpRequests()
		.requestMatchers("/api/services/user/login/**")
		.permitAll()
		.and()
		.httpBasic()
		.and()
		.sessionManagement()
        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
		.and()
		.addFilter(new JWTAuthenticationFilter(authenticationManager))
        .addFilter(new JWTAuthorizationFilter(authenticationManager))
//		.authenticationProvider(authenticationProvider)
        .build();		
	}
	
	@Bean
    public PasswordEncoder passwordEncoder() {
        return  new BCryptPasswordEncoder();
    }
}
