package com.bordeaux.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bordeaux.entity.Sprint;
import com.bordeaux.entity.Task;

@Repository
public interface TaskRepository extends JpaRepository<Task, Integer> {
	public Task findBySprint(Sprint s);
}
