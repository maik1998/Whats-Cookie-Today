package com.proyecto.service;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.proyecto.model.UserType;

public interface UserTypeService {

	public UserType saveUserType (UserType userType);
	
	public Iterable<UserType> findAll();
	
	public Page <UserType> findAll(Pageable pageable);
	
	public Optional <UserType> findById(Long id);
	
	public void deleteByIdUserType (Long id);
	
}
