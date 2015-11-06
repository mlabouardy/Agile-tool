package com.labouardy.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.labouardy.entity.Backlog;

@Repository
public interface BacklogRepository extends JpaRepository<Backlog, Integer>{

}
