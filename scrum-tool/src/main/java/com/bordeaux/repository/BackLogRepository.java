package com.bordeaux.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bordeaux.entity.BackLog;

@Repository
public interface BackLogRepository extends JpaRepository<BackLog, Integer>{

}
