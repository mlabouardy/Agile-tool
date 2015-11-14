package com.bordeaux.service.user;

import java.util.HashMap;
import java.util.Map;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bordeaux.entity.UserStory;
import com.bordeaux.entity.user.ScrumMaster;
import com.bordeaux.repository.user.ScrumMasterRepository;

@Service
@Transactional
public class ScrumMasterService {

	@Autowired
	private ScrumMasterRepository scrumMasterRepository;

	public ScrumMaster findUserByEmail(String email) {
		return scrumMasterRepository.findByEmail(email);
	}

	public ScrumMaster findUserById(int id) {
		return scrumMasterRepository.findOneById(id);
	}
	
	public Map<Integer, String> getScrumMasterList() {

		Map<Integer, String> scrumMasterList = new HashMap<Integer, String>();

		for (ScrumMaster scrumMaster : scrumMasterRepository.findAll()) {
			scrumMasterList.put(scrumMaster.getId(), scrumMaster.getFirstname() + " " + scrumMaster.getLastname());
		}

		return scrumMasterList;
	}

	public ScrumMaster addUserStoryToScrumMaster(int scrumMasterId, UserStory userStory) {

		ScrumMaster scrumMaster = scrumMasterRepository.findOneById(scrumMasterId);

		for (UserStory us : scrumMaster.getUserStories()) {
			if (us.getId() == userStory.getId())
				return scrumMaster;
		}

		removeUserStoryFromScrumMaster(userStory.getId());
		
		scrumMaster.getUserStories().add(userStory);
		scrumMasterRepository.saveAndFlush(scrumMaster);

		return scrumMaster;
		
	}
	
	public void removeUserStoryFromScrumMaster(int userStoryID) {
		
		for (ScrumMaster sm : scrumMasterRepository.findAll()) {
			
			UserStory removeUserStory = null;
			
			for (UserStory us : sm.getUserStories()) {
				if (us.getId() == userStoryID) {
					removeUserStory = us;
					break;
				}
			}
			
			if (removeUserStory != null) {
				sm.getUserStories().remove(removeUserStory);
				scrumMasterRepository.saveAndFlush(sm);
			}
		}

	}

	public ScrumMaster getScrumMasterByUserStoryID(int userStoryID) {

		for (ScrumMaster scrumMaster : scrumMasterRepository.findAll()) {
			for (UserStory userStory : scrumMaster.getUserStories()) {
				if (userStory.getId() == userStoryID) {
					return scrumMaster;
				}
			}
		}

		return null;
	}

}
