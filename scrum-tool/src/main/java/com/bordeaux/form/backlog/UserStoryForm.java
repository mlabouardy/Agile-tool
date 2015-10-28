package com.bordeaux.form.backlog;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

public class UserStoryForm {

	private int id;

	@NotEmpty(message = "The tag field can not be empty")
	@Size(min=3,max=20, message ="The size of the tag should be between {min} and {max} characters")
	private String tag;

	@NotEmpty(message = "The description field can not be empty")
	@Size(min=10,max=5000, message ="The size of the description should be between {min} and {max} characters")
	private String description;

	@NotNull(message = "The priority field can not be empty")
	@Min(0)
	@Max(100)
	private int priority;

	@NotNull(message = "The difficulty field can not be empty")
	@Min(0)
	@Max(100)
	private int difficulty;

	private String exception;
	
	// dependance choisi par l'utilisateur durant la saisie
	private List<Integer> selectedDependencies;
	
	// toutes les dependances possibles (sauf lui meme)
	private List<Integer> dependencies;
	
	public UserStoryForm() {
		this.dependencies = new ArrayList<Integer>();
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
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
	
	public List<Integer> getDependencies() {
		return dependencies;
	}
	
	public void setDependencies(List<Integer> dependencies) {
		this.dependencies = dependencies;
	}

	public List<Integer> getSelectedDependencies() {
		return selectedDependencies;
	}
	
	public void setSelectedDependencies(List<Integer> selectedDependencies) {
		this.selectedDependencies = selectedDependencies;
	}

	public String getException() {
		return exception;
	}
	
	public void setException(String exception) {
		this.exception = exception;
	}
	
}
