package com.bordeaux.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bordeaux.entity.UserStory;
import com.bordeaux.entity.UserStoryDependencies;

public interface UserStoryDependenciesRepository  extends JpaRepository<UserStoryDependencies, Integer>{

	public UserStoryDependencies findOneByParent(UserStory parent);
	
}
