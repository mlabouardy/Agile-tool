package com.bordeaux.repository.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bordeaux.entity.user.ScrumMaster;

@Repository
public interface ScrumMasterRepository extends JpaRepository<ScrumMaster, Integer>{

	public ScrumMaster findByEmail(String email);

}
