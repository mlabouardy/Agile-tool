package com.bordeaux.entity.user;

import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;

import com.bordeaux.entity.Role.RoleType;
import com.bordeaux.entity.Task;

@Entity
public class ScrumTeam extends User{

	public ScrumTeam() {
		this.setRole(RoleType.TEAM.getRole());
	}
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Collection<Task> tasks;
	
	public ScrumTeam(int id){
		super(id);
	}
	
	public Collection<Task> getTasks() {
		return tasks;
	}
	
	public void setTasks(Collection<Task> tasks) {
	this.tasks = tasks;
	}
}
