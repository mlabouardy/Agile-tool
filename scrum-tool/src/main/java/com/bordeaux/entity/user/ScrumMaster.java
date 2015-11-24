package com.bordeaux.entity.user;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.bordeaux.entity.Role.RoleType;
import com.bordeaux.entity.Project;
import com.bordeaux.entity.UserStory;

@Entity
public class ScrumMaster extends User {

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Collection<UserStory> userStories;
	
	@JoinTable(name="team")
	@OneToMany(targetEntity=ScrumTeam.class, fetch = FetchType.EAGER)
	private Collection<ScrumTeam> scrumTeamList;
	
	@OneToOne
	private Project project;
	
	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

	public ScrumMaster() {
		this.setRole(RoleType.MASTER.getRole());
		this.scrumTeamList = new ArrayList<ScrumTeam>();
	}
	
	public Collection<UserStory> getUserStories() {
		return userStories;
	}
	
	public void setUserStories(Collection<UserStory> userStories) {
		this.userStories = userStories;
	}
	
	public Collection<ScrumTeam> getScrumTeamList() {
		return scrumTeamList;
	}
	
	public void setScrumTeamList(Collection<ScrumTeam> scrumTeamList) {
		this.scrumTeamList = scrumTeamList;
	}
}
