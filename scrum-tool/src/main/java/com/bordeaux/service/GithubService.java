package com.bordeaux.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bordeaux.entity.Github;
import com.bordeaux.repository.GithubRepository;

@Service
@Transactional
public class GithubService {

	@Autowired
	private GithubRepository githubRepository;
	
	public void save(Github github){
		githubRepository.save(github);
	}
}
