package com.bordeaux.entity.user;

import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;

import com.bordeaux.entity.Role.RoleType;
import com.bordeaux.entity.UserStory;

@Entity
public class ScrumMaster extends User {

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Collection<UserStory> userStories;
	
	public ScrumMaster() {
		this.setRole(RoleType.MASTER.getRole());
	}
	
	public Collection<UserStory> getUserStories() {
		return userStories;
	}
	
	public void setUserStories(Collection<UserStory> userStories) {
		this.userStories = userStories;
	}
}
