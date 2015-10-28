package com.bordeaux.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.bordeaux.entity.BackLog;
import com.bordeaux.entity.UserStory;
import com.bordeaux.entity.UserStoryDependencies;
import com.bordeaux.form.backlog.BackLogForm;
import com.bordeaux.form.backlog.UserStoryForm;
import com.bordeaux.repository.BackLogRepository;
import com.bordeaux.repository.UserStoryDependenciesRepository;
import com.bordeaux.repository.UserStoryRepository;

@Service
@Transactional
public class BackLogService {

	@Autowired
	private BackLogRepository backLogRepository;

	@Autowired
	private UserStoryRepository userStoryRepository;

	@Autowired
	private UserStoryDependenciesRepository userStoryDependenciesRepository;

	private BackLog getSingletonBackLog() {

		BackLog backLog = backLogRepository.findOne(1);

		if (backLog == null) {
			backLog = backLogRepository.save(new BackLog());
		}

		return backLog;

	}

	public UserStory addOrUpdateUserStory(UserStory userStory) {

		BackLog backLog = getSingletonBackLog();

		if (userStory.getId() == 0) {

			backLog.getUserStories().add(userStory);
			return userStoryRepository.save(userStory);

		} else {

			for (UserStory us : backLog.getUserStories()) {
				if (us.getId() == userStory.getId()) {
					us.setTag(userStory.getTag());
					us.setPriority(userStory.getPriority());
					us.setDifficulty(userStory.getDifficulty());
					us.setDescription(userStory.getDescription());
					return userStoryRepository.save(us);
				}
			}

			return null;

		}
	}

	public void addOrUpdateUserStoryDependencies(int userStoryID, List<Integer> userStoriesDependenciesID) {

		UserStory parent = userStoryRepository.findOne(userStoryID);
		Collection<UserStory> childs = userStoryRepository.findAll(userStoriesDependenciesID);

		if (parent != null) {

			UserStoryDependencies usd = userStoryDependenciesRepository.findOneByParent(parent);

			if (usd != null) { // update
				usd.setDependencies(childs);
				userStoryDependenciesRepository.save(usd);
			} else { // add
				BackLog backLog = getSingletonBackLog();
				backLog.addDependencies(new UserStoryDependencies(parent, childs));
				backLogRepository.save(backLog);
			}
		}
	}

	public void removeUserStory(int userStoryID){
		UserStoryDependencies usd = userStoryDependenciesRepository.findOneByParent(userStoryRepository.findOne(userStoryID));
		userStoryDependenciesRepository.delete(usd);
		userStoryRepository.delete(userStoryID);
	}
	
	private Map<Integer, ArrayList<Integer>> getDependenciesID() {

		BackLog backLog = getSingletonBackLog();

		Collection<UserStoryDependencies> userStoryDependenciesList = backLog.getDependencies();

		if (userStoryDependenciesList != null) {
			Map<Integer, ArrayList<Integer>> dependencies = new HashMap<Integer, ArrayList<Integer>>();

			for (UserStoryDependencies usd : userStoryDependenciesList) {
				ArrayList<Integer> usId = new ArrayList<Integer>();
				for (UserStory us : usd.getDependencies()) {
					usId.add(us.getId());
				}
				dependencies.put(usd.getParent().getId(), usId);
			}

			return dependencies;
		} else {
			return null;
		}

	}

	public List<Integer> getAllUserStoriesIdExceptId(int removeId) {

		BackLog backLog = getSingletonBackLog();

		List<Integer> collection = new ArrayList<Integer>();
		Collection<UserStory> userStories = backLog.getUserStories();

		for (UserStory userStory : userStories) {
			if (userStory.getId() != removeId) {
				collection.add(userStory.getId());
			}
		}
		return collection;
	}

	private Collection<UserStoryForm> getUserStoryFormList() {

		BackLog backLog = getSingletonBackLog();

		Collection<UserStoryForm> userStoriesForm = new ArrayList<UserStoryForm>();
		Collection<UserStory> userStories = backLog.getUserStories();
		Map<Integer, ArrayList<Integer>> dependenciesID = getDependenciesID();

		for (UserStory userStory : userStories) {
			UserStoryForm userStoryForm = new UserStoryForm();
			userStoryForm.setId(userStory.getId());
			userStoryForm.setTag(userStory.getTag());
			userStoryForm.setDescription(userStory.getDescription());
			userStoryForm.setDifficulty(userStory.getDifficulty());
			userStoryForm.setPriority(userStory.getPriority());
			userStoryForm.setDependencies(getAllUserStoriesIdExceptId(userStory.getId()));
			userStoryForm.setSelectedDependencies(dependenciesID.get(userStory.getId()));
			userStoriesForm.add(userStoryForm);
		}

		return userStoriesForm;

	}

	public Model initModelForBackLogPage(Model model, String... exception) {
		BackLogForm backLogForm = new BackLogForm();
		if (exception.length > 0)
			backLogForm.setException(Arrays.toString(exception));
		backLogForm.setUserStoryFormList(getUserStoryFormList());
		model.addAttribute("backLogForm", backLogForm);
		return model;
	}

	public UserStoryForm getUserStoryForm(int userStoryID) {

		UserStoryForm userStoryForm = new UserStoryForm();
		UserStory userStory = userStoryRepository.findOne(userStoryID);

		if (userStory != null) {
			userStoryForm.setId(userStory.getId());
			userStoryForm.setTag(userStory.getTag());
			userStoryForm.setDescription(userStory.getDescription());
			userStoryForm.setDifficulty(userStory.getDifficulty());
			userStoryForm.setPriority(userStory.getPriority());
			userStoryForm.setDependencies(getAllUserStoriesIdExceptId(userStory.getId()));
			userStoryForm.setSelectedDependencies(getDependenciesID().get(userStory.getId()));
		} else {
			userStoryForm.setDependencies(getAllUserStoriesIdExceptId(userStoryID));
		}

		return userStoryForm;
	}

	public UserStory generateUserStory(UserStoryForm userStoryForm) {

		UserStory userStory = new UserStory();
		userStory.setId(userStoryForm.getId());
		userStory.setTag(userStoryForm.getTag());
		userStory.setDescription(userStoryForm.getDescription());
		userStory.setDifficulty(userStoryForm.getDifficulty());
		userStory.setPriority(userStoryForm.getPriority());

		return userStory;
	}

	
}
