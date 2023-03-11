package com.security.practice.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.SecondaryTable;
import jakarta.persistence.Table;

@Entity
@Table(name="Users")
@SecondaryTable(name = "UserRoles")
public class User  implements Serializable {

	private static final long serialVersionUID = -2995612329560865341L;

	@Id
	@Column(name = "email", nullable = false, unique = true)
	private String email;
	
	@Column(name = "user_name", nullable = false)
	private String UserName;
	
	@Column(name = "password", nullable = false)
	private String password;
	
	@Column(name = "enabled", nullable = false)
	private boolean enabled;
	
	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(
            name = "UserRoles",
            joinColumns = { @JoinColumn(name = "email") },
            inverseJoinColumns = { @JoinColumn(name = "roleId") })
    private Set<Roles> userAuthorities = new HashSet<>();
	
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getUserName() {
		return UserName;
	}
	
	public void setUserName(String userName) {
		UserName = userName;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public boolean isEnabled() {
		return enabled;
	}
	
	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}
	
	public Set<Roles> getUserAuthorities() {
		return userAuthorities;
	}
	
	public void setUserAuthorities(Set<Roles> userAuthorities) {
		this.userAuthorities = userAuthorities;
	}
	
	public User(String email, String userName, String password, boolean enabled, Set<Roles> userAuthorities) {
		super();
		this.email = email;
		this.UserName = userName;
		this.password = password;
		this.enabled = enabled;
		this.userAuthorities = userAuthorities;
	}
	
	public User() {
		super();
	}

	public User(User employee) {
		this.email = employee.getEmail();
		this.UserName = employee.getUserName();
		this.password = employee.getPassword();
		this.enabled = employee.isEnabled();
		this.userAuthorities = employee.getUserAuthorities();
	}
}
