package com.bordeaux.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Task {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private String Tag;
	private String Description;
	private String color;
	
	@OneToOne
	private UserStory userStory;
	
	public UserStory getUserStory() {
		return userStory;
	}
	public void setUserStory(UserStory userStory) {
		this.userStory = userStory;
	}
	public Task() {
		// TODO Auto-generated constructor stub
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public String getTag() {
		return Tag;
	}
	public void setTag(String tag) {
		Tag = tag;
	}
	public String getDescription() {
		return Description;
	}
	public void setDescription(String description) {
		Description = description;
	}
	
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	
	@Override
	public String toString() {
		return "Task [id=" + id + ", Tag=" + Tag + ", Description=" + Description + ",color=" + color +"]";
	}
}
