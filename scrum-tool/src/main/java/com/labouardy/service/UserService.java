package com.labouardy.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.mockito.internal.util.collections.ArrayUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.labouardy.entity.User;
import com.labouardy.repository.RoleRepository;
import com.labouardy.repository.UserRepository;

@Service
@Transactional
public class UserService {

	@Autowired
	private UserRepository userRepository;
	
	private RoleRepository roleRepository;
	public void save(User user) {
		userRepository.save(user);
	}

	public User findUserByEmail(String email) {
		return userRepository.findByEmail(email);
	}

	public List<User> findAll() {
		return userRepository.findAll();
	}

	public List<User> findAllScrumMaster() {
		List<User> users=findAll();
		List<User> masters=new ArrayList<User>();
		for(User u:users){
			if(u.getRole().getId()==2)
				masters.add(u);
		}
		return masters;
	}
}
