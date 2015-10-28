package com.bordeaux.entity;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;

@Entity
public class UserStoryDependencies {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@OneToOne
	@JoinColumn(name = "userStory_id")
	private UserStory parent;

	@ManyToMany
	@JoinTable(name = "dependency_userStory", joinColumns = @JoinColumn(name = "dependency_id") , inverseJoinColumns = @JoinColumn(name = "userStory_id") )
	private Collection<UserStory> dependencies;

	public UserStoryDependencies() {
		this.dependencies = new ArrayList<UserStory>();
	}
	
	public UserStoryDependencies(UserStory parent, Collection<UserStory> dependencies) {
		this.parent = parent;
		this.dependencies = dependencies;
	}

	public UserStory getParent() {
		return parent;
	}
	
	public void setDependencies(Collection<UserStory> dependencies) {
		this.dependencies = dependencies;
	}
	
	public Collection<UserStory> getDependencies() {
		return dependencies;
	}
}