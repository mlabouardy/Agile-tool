package com.bordeaux.form.backlog;

public class TypeForm {
	
	//le type du traitement
	private String type;
	
	// id dans le cas de modification
	private int id;
	
	public TypeForm() {
	}
	
	public String getType() {
		return type;
	}
	
	public void setType(String type) {
		this.type = type;
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
}
