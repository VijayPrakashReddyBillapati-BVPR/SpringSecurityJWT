package com.security.practice.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class UserInfo implements UserDetails {

	private static final long serialVersionUID = 5133916299009937704L;
	private String email;
	private String password;
	private boolean enabled;
	private List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
	
	public UserInfo(User user) {
		this.email = user.getEmail();
		this.password = user.getEmail();
		this.enabled = user.isEnabled();
		for(Roles roles : user.getUserAuthorities()) {
			GrantedAuthority authority = new SimpleGrantedAuthority(roles.getRoleName());
			this.authorities.add(authority);
		}
		
	}
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {
		return email;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return enabled;
	}

}
