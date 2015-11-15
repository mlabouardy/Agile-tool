package com.bordeaux.repository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.bordeaux.entity.Sprint;
import org.springframework.stereotype.Repository;

@Repository
public interface SprintRepository extends JpaRepository<Sprint , Integer> {

	
}
