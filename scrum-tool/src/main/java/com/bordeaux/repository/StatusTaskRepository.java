package com.bordeaux.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.bordeaux.entity.StatusTask;
import org.springframework.stereotype.Repository;

@Repository
public interface StatusTaskRepository extends JpaRepository<StatusTask , Integer>{

	
	public StatusTask findById(int id);
	
	
	
}
