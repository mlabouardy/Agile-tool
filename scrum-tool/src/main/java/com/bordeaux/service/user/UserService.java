package com.bordeaux.service.user;

import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bordeaux.entity.user.User;
import com.bordeaux.repository.user.UserRepository;

@Service
@Transactional
public class UserService {

	@Autowired
	private UserRepository userRepository;
	
	public void save(User user) {
		userRepository.save(user);
	}
	
	public User findUserByEmail(String email) {
		return userRepository.findByEmail(email);
	}
	
	public User findUserById(int id){
		return userRepository.findById(id);
	}
	
	public Collection<User> findEveryUsers(){
		return userRepository.findAll();
	}
}
