package com.bordeaux.repository;
import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bordeaux.entity.Sprint;
import com.bordeaux.entity.user.User;

import org.springframework.stereotype.Repository;

@Repository
public interface SprintRepository extends JpaRepository<Sprint , Integer> {

	public Sprint findSprintById(int id);
	
	public Collection<Sprint> findSprintsByOwner(User u);
	
}
