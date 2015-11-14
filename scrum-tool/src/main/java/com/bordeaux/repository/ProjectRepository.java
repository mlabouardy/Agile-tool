package com.bordeaux.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bordeaux.entity.Project;

public interface ProjectRepository extends JpaRepository<Project, Integer>{

}
