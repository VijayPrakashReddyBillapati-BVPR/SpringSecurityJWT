//package com.security.practice.model;
//
//import java.io.Serializable;
//
//import jakarta.persistence.Entity;
//import jakarta.persistence.GeneratedValue;
//import jakarta.persistence.GenerationType;
//import jakarta.persistence.Id;
//import jakarta.persistence.JoinColumn;
//import jakarta.persistence.ManyToOne;
//import jakarta.persistence.Table;
//
//@Entity
//@Table(name = "UserRoles")
//public class UserRoles  implements Serializable {
//
//	private static final long serialVersionUID = 5901090063824751330L;
//	
//	@Id
//	@GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "UserRoleSequence")
//	private Long id;
//	
//	 @ManyToOne
//	 @JoinColumn(name = "email")
//	 private User user;
//	 
//	 @ManyToOne
//	 @JoinColumn(name = "roleId")
//	 private Roles roles;
//	
//	
//	public Long getId() {
//		return id;
//	}
//
//	public void setId(Long id) {
//		this.id = id;
//	}
//
//	public User getUser() {
//		return user;
//	}
//
//	public void setUser(User user) {
//		this.user = user;
//	}
//
//	public Roles getRoles() {
//		return roles;
//	}
//
//	public void setRoles(Roles roles) {
//		this.roles = roles;
//	}
//
//	public static long getSerialversionuid() {
//		return serialVersionUID;
//	}
//
//	public UserRoles() {
//		super();
//	}
//	
//	public UserRoles(Long id, User user, Roles roles) {
//		super();
//		this.id = id;
//		this.user = user;
//		this.roles = roles;
//	}
//}
