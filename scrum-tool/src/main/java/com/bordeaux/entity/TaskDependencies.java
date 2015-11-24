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
public class TaskDependencies {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@OneToOne
	@JoinColumn(name = "task_id")
	private Task task;

	@ManyToMany
	@JoinTable(name = "dependency_task", joinColumns = @JoinColumn(name = "dependency_id") , inverseJoinColumns = @JoinColumn(name = "task_id") )
	private Collection<Task> dependencies;

	public TaskDependencies(){
		dependencies = new ArrayList<Task>();
	}
	
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Task getParent() {
		return task;
	}

	public void setParent(Task parent) {
		this.task = parent;
	}

	public Collection<Task> getDependencies() {
		return dependencies;
	}

	public void setDependencies(Collection<Task> dependencies) {
		this.dependencies = dependencies;
	}

	
	
	
}
