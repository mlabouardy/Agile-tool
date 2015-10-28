package com.bordeaux.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class UserStory {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private String tag;

	private String description;

	private int priority;

	private int difficulty;

	public int getId() {
		return id;
	}

	public String getDescription() {
		return description;
	}

	public int getDifficulty() {
		return difficulty;
	}

	public int getPriority() {
		return priority;
	}

	public String getTag() {
		return tag;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setDifficulty(int difficulty) {
		this.difficulty = difficulty;
	}

	public void setPriority(int priority) {
		this.priority = priority;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return "UserStory [id=" + id + ", tag=" + tag + ", description=" + description + ", priority=" + priority
				+ ", difficulty=" + difficulty + "]";
	}
	
}