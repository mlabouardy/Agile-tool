package com.bordeaux.form.backlog;

import java.util.Collection;

public class BackLogForm {

	private Collection<UserStoryForm> userStoryFormList;
	
	private TypeForm typeForm;

	private String exception;
	
	public BackLogForm() {
	}

	public Collection<UserStoryForm> getUserStoryFormList() {
		return userStoryFormList;
	}
	
	public void setUserStoryFormList(Collection<UserStoryForm> userStoryFormList) {
		this.userStoryFormList = userStoryFormList;
	}
	
	public TypeForm getTypeForm() {
		return typeForm;
	}

	public void setTypeForm(TypeForm typeForm) {
		this.typeForm = typeForm;
	}
	
	public String getException() {
		return exception;
	}
	
	public void setException(String exception) {
		this.exception = exception;
	}

}
