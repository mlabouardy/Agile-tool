package com.bordeaux.entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Project {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	@OneToOne(cascade = CascadeType.ALL)
	private BackLog backLog;
	
	@OneToOne
	private Github github;
	
	public Project() {
		backLog = new BackLog();
	}
	
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public BackLog getBackLog() {
		return backLog;
	}
	
	public void setBackLog(BackLog backLog) {
		this.backLog = backLog;
	}


	public Github getGithub() {
		return github;
	}


	public void setGithub(Github github) {
		this.github = github;
	}
	
}
