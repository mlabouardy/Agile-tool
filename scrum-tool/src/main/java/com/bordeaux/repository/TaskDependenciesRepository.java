package com.bordeaux.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bordeaux.entity.Task;
import com.bordeaux.entity.TaskDependencies;

public interface TaskDependenciesRepository extends JpaRepository<TaskDependencies, Integer> {

	public TaskDependencies findByTask(Task task);
	
}
