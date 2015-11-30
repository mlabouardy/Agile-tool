package com.bordeaux.repository;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bordeaux.entity.Sprint;
import com.bordeaux.entity.StatusTask;
import com.bordeaux.entity.Task;

@Repository
public interface TaskRepository extends JpaRepository<Task, Integer> {
	public Task findBySprint(Sprint s);
	
	public Task findById(int id);
	
	public Collection<Task> findByStatus(StatusTask status);
}
