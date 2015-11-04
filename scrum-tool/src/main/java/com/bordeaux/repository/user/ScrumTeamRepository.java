package com.bordeaux.repository.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bordeaux.entity.user.ScrumTeam;

@Repository
public interface ScrumTeamRepository extends JpaRepository<ScrumTeam, Integer>{

	public ScrumTeam findByEmail(String email);

}
