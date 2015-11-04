package com.bordeaux.entity;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

@Entity
public class BackLog {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "backLog_id", nullable = false)
	private Collection<UserStory> userStories;

	@OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="backLog_id", nullable=false)
	private Collection<UserStoryDependencies> dependencies;
	
	public BackLog() {
		userStories = new ArrayList<UserStory>();
	}

	public int getId() {
		return id;
	}
	
	public Collection<UserStory> getUserStories() {
		return userStories;
	}
	
	public Collection<UserStoryDependencies> getDependencies() {
		return dependencies;
	}
	
	public UserStoryDependencies getUserStoryDependecies(int userStoryID) {
		for (UserStoryDependencies userStoryDependencies : dependencies){
			if (userStoryDependencies.getParent().getId()==userStoryID){
				return userStoryDependencies;
			}
		}
		return null;
	}
	
	public void addDependencies(UserStoryDependencies dependency) {
		this.dependencies.add(dependency);
	}

	@Override
	public String toString() {
		return "BackLog [id=" + id + ", userStories=" + userStories + ", dependencies=" + dependencies + "]";
	}
	
	
}