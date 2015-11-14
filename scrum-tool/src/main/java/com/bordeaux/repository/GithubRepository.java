package com.bordeaux.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bordeaux.entity.Github;

@Repository
public interface GithubRepository extends JpaRepository<Github, Integer>{
	
}
