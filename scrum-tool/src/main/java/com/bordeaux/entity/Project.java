package com.bordeaux.entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Project {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	@OneToOne(cascade = CascadeType.ALL)
	private BackLog backLog;
	
	public Project() {
		backLog = new BackLog();
	}
	
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public BackLog getBackLog() {
		return backLog;
	}
	
	public void setBackLog(BackLog backLog) {
		this.backLog = backLog;
	}
	
}
