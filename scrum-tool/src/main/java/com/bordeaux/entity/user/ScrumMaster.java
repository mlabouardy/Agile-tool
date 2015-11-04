package com.bordeaux.entity.user;

import javax.persistence.Entity;

import com.bordeaux.entity.Role.RoleType;

@Entity
public class ScrumMaster extends User {

	public ScrumMaster() {
		this.setRole(RoleType.MASTER.getRole());
	}
}
