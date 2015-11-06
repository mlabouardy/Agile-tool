package com.labouardy.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Team {

	@Id @GeneratedValue
	private int id;
	
	private String name;
	
	@OneToOne
	private User scrum_master;
	
	@OneToOne
	private Project project;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public User getScrum_master() {
		return scrum_master;
	}

	public void setScrum_master(User scrum_master) {
		this.scrum_master = scrum_master;
	}

	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}
	
}
