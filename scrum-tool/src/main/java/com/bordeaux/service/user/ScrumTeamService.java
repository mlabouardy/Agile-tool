package com.bordeaux.service.user;

import java.util.Collection;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bordeaux.entity.user.ScrumMaster;
import com.bordeaux.entity.user.ScrumTeam;
import com.bordeaux.repository.user.ScrumMasterRepository;
import com.bordeaux.repository.user.ScrumTeamRepository;

@Service
@Transactional
public class ScrumTeamService {

	@Autowired
	private ScrumTeamRepository scrumTeamRepository;
	
	@Autowired
	private ScrumMasterRepository scrumMasterRepository;
	
	public ScrumTeam findUserByEmail(String email) {
		return scrumTeamRepository.findByEmail(email);
	}
	
	public Collection<ScrumTeam> findAll(){
		return scrumTeamRepository.findAll();
	}
	
	public Collection<ScrumTeam> findDevWithoutTeam(){
		
		Set<Integer> scrumTeamIDWithGroup = new HashSet<Integer>();
		
		for (ScrumMaster scrumMaster : scrumMasterRepository.findAll()){
			for (ScrumTeam scrumTeam : scrumMaster.getScrumTeamList()){
				scrumTeamIDWithGroup.add(scrumTeam.getId());
			}
		}
		
		Collection<ScrumTeam> result = new ArrayList<ScrumTeam>();
		
		for (ScrumTeam scrumTeam : scrumTeamRepository.findAll()){
			if (!scrumTeamIDWithGroup.contains(scrumTeam.getId())){
				result.add(scrumTeam);
			}
		}
		
		return result;
		
	}
	
	
	public ScrumTeam findByID(int id){
		return scrumTeamRepository.findOne(id);	
	}

	/*****************/
	public List<ScrumTeam> getScrumTeamList() {

		List<ScrumTeam> scrumTeamList = scrumTeamRepository.findAll();
		return scrumTeamList;
	}
	
	/*****************/
}
