package com.bordeaux.entity;

import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.bordeaux.entity.user.ScrumMaster;
import com.bordeaux.entity.user.ScrumTeam;
import com.bordeaux.entity.user.User;

@Entity
public class Project {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	private String name;
	
	@OneToOne(cascade = CascadeType.ALL)
	private BackLog backLog;
	
	@OneToOne
	private Github github;
	
	@OneToMany
	private Collection<Sprint> sprintList;

	@OneToMany
	private Collection<ScrumMaster> managers;
	
	@OneToMany
	private Collection<ScrumTeam> devs;
	
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
	
	public Collection<Sprint> getSprintList() {
		return sprintList;
	}


	public void setSprintList(Collection<Sprint> sprintList) {
		this.sprintList = sprintList;
	}


	public Collection<ScrumMaster> getManagers() {
		return managers;
	}


	public void setManagers(Collection<ScrumMaster> managers) {
		this.managers = managers;
	}


	public Collection<ScrumTeam> getDevs() {
		return devs;
	}


	public void setDevs(Collection<ScrumTeam> devs) {
		this.devs = devs;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}

	
	
}
