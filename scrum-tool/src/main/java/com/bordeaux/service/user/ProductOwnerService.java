package com.bordeaux.service.user;

import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bordeaux.entity.user.ProductOwner;
import com.bordeaux.repository.user.ProductOwnerRepository;

@Service
@Transactional
public class ProductOwnerService {

	@Autowired
	private ProductOwnerRepository productOwnerRepository;
	
	public ProductOwner findUserByEmail(String email) {
		return productOwnerRepository.findByEmail(email);
	}
	
	public Collection<ProductOwner> findAll(){
		return productOwnerRepository.findAll();
	}
}
