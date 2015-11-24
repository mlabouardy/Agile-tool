package com.bordeaux.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bordeaux.entity.Project;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Integer>{

	public Project findById(int id);
	
}
