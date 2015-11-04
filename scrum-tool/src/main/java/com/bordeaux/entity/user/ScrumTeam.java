package com.bordeaux.entity.user;

import javax.persistence.Entity;

import com.bordeaux.entity.Role.RoleType;

@Entity
public class ScrumTeam extends User{

	public ScrumTeam() {
		this.setRole(RoleType.TEAM.getRole());
	}
}
