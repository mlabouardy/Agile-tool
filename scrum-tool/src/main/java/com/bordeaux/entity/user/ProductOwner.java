package com.bordeaux.entity.user;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToOne;

import org.codehaus.jackson.annotate.JsonIgnore;

import com.bordeaux.entity.Project;
import com.bordeaux.entity.Role.RoleType;

@Entity
public class ProductOwner extends User{
	
	@OneToOne(cascade = CascadeType.ALL)
	private Project project;
	
	public ProductOwner() {
		this.setRole(RoleType.PRODUCT.getRole());
	}
	
	public Project getProject() {
		return project;
	}
	
	public void setProject(Project project) {
		this.project = project;
	}
}
