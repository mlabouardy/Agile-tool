package com.bordeaux.entity.pert;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;

/*a utilisé uniquement par l'agorithme de pert (inaccessible depuis d'autres packages*/
public class Task {
		
	private String name;
	private float duration;
	private List<Task> list;

	public Task(String name, float duration) {
		this.list = new ArrayList<Task>();
		this.name = name;
		this.duration = duration;
	}

	public void addDependency(Task... taskList) {
		for (Task task : taskList) {
			this.list.add(task);
		}
	}

	public List<Task> getDependencies() {
		return this.list;
	}

	public Task hasDependency(Task task){
		if (this.list.contains(task)) return task;
		else return null;
	}

	public String getName() {
		return this.name;
	}

	public float getDuration() {
		return duration;
	}

	public String toString() {
		return new Gson().toJson(this);
	}

	public void setDuration(float duration) {
		this.duration = duration;
	}
}
