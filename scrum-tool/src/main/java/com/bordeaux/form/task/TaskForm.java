package com.bordeaux.form.task;

import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

public class TaskForm {

	private int id;
	
	@NotEmpty(message = "The tag field can not be empty")
	@Size(min=3,max=20, message ="The size of the tag should be between {min} and {max} characters")
	private String Tag;
	
	@NotEmpty(message = "The tag field can not be empty")
	@Size(min=3,max=20, message ="The size of the tag should be between {min} and {max} characters")
	private String Description;
	
	private String color;
	private String exception;
	private String userStoryId;
	/************************/
	@NotEmpty(message = "The tag field can not be empty")
	private int scrumTeamId;
	
	public int getScrumTeamId() {
		return scrumTeamId;
	}
	
	public void setScrumTeamId(int scrumTeamId) {
		this.scrumTeamId = scrumTeamId;
	}
	/****************/
	
	public String getuserStoryId() {
		return userStoryId;
	}
	public void setuserStoryId(String userStoryId) {
		this.userStoryId = userStoryId;
	}
   
	public TaskForm() {
	
	}
	public void setException(String exception) {
		this.exception = exception;
	}
	public String getException() {
		return exception;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getDescription() {
		return Description;
	}
	public void setDescription(String description) {
		Description = description;
	}
	public String getTag() {
		return Tag;
	}
	public void setTag(String tag) {
		Tag = tag;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	
}
