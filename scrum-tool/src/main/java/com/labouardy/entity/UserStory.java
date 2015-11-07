package com.labouardy.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
public class UserStory {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	
	private String tag;
	
	private String name;
	
	private int priority;
	
	private int difficulty;
	
	@ManyToOne
	private Backlog backlog;
	
	@ManyToMany
	private List<UserStory> dependancies;

	public List<UserStory> getDependancies() {
		return dependancies;
	}

	public void setDependancies(List<UserStory> dependancies) {
		this.dependancies = dependancies;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getPriority() {
		return priority;
	}

	public void setPriority(int priority) {
		this.priority = priority;
	}

	public int getDifficulty() {
		return difficulty;
	}

	public void setDifficulty(int difficulty) {
		this.difficulty = difficulty;
	}

	public Backlog getBacklog() {
		return backlog;
	}

	public void setBacklog(Backlog backlog) {
		this.backlog = backlog;
	}
	
	
}
