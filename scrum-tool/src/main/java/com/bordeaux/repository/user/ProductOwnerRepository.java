package com.bordeaux.repository.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bordeaux.entity.user.ProductOwner;

@Repository
public interface ProductOwnerRepository extends JpaRepository<ProductOwner, Integer>{

	public ProductOwner findByEmail(String email);

}
