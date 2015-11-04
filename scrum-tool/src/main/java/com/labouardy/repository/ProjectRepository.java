package com.labouardy.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.labouardy.entity.Project;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Integer>{

}
