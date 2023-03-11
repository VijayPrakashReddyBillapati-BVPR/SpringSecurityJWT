package com.security.practice.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;

@Entity
@Table(name = "Roles")
public class Roles  implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	private Long roleId;
	
	@Column(name = "Role_name",nullable = false,unique = true)
	private String roleName;

	
	@ManyToMany(cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
    }, fetch = FetchType.EAGER,
    mappedBy = "userAuthorities")
    private Set<User> usersRoles = new HashSet<>();


	public Roles() {
		super();
	}

	public Long getRoleId() {
		return roleId;
	}

	public void setRoleId(Long id) {
		roleId = id;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public Set<User> getUsersRoles() {
		return usersRoles;
	}

	public void setUsersRoles(Set<User> usersRoles) {
		this.usersRoles = usersRoles;
	}

	public Roles(Long roleId, String roleName, Set<User> usersRoles) {
		super();
		this.roleId = roleId;
		this.roleName = roleName;
		this.usersRoles = usersRoles;
	}
	
	
}
