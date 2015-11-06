package com.labouardy.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Backlog {
	
	@Id
	@GeneratedValue
	private int id;
	
	@OneToOne
	private Project project;
	
	@OneToMany(mappedBy="backlog")
	private List<UserStory> userstories;
	

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

	public List<UserStory> getUserstories() {
		return userstories;
	}

	public void setUserstories(List<UserStory> userstories) {
		this.userstories = userstories;
	}
	
}
