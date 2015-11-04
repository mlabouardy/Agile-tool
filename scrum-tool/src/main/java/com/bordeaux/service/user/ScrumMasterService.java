package com.bordeaux.service.user;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
