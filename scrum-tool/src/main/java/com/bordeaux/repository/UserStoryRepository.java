package com.bordeaux.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bordeaux.entity.UserStory;

@Repository
public interface UserStoryRepository  extends JpaRepository<UserStory, Integer>{

	
}
