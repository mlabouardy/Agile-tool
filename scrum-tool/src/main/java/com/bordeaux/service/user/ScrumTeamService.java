package com.bordeaux.service.user;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bordeaux.entity.user.ScrumTeam;
import com.bordeaux.repository.user.ScrumTeamRepository;

@Service
@Transactional
public class ScrumTeamService {

	@Autowired
	private ScrumTeamRepository scrumTeamRepository;
	
	public ScrumTeam findUserByEmail(String email) {
		return scrumTeamRepository.findByEmail(email);
	}
}
