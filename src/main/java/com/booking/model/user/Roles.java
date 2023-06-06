package com.booking.model.user;

import javax.persistence.*;





@Entity
@Table(name="roles")
public class Roles {

	@Id
	@Column(name="role_id")
	private Integer roleId;

	private String role;
	private String name;

	
	public Integer getRoleId() {
		return this.roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	public String getRole() {
		return this.role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
