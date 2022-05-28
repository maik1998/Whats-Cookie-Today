package com.proyecto.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.proyecto.model.UserType;
import com.proyecto.repository.UserTypeRepository;

@Service
public class UserTypeServiceImpl implements UserTypeService{

	@Autowired
	private UserTypeRepository UserTypeRepository;
	
	@Override
	@Transactional
	public UserType saveUserType(UserType userType) {
		return UserTypeRepository.save(userType);
	}

	@Override
	@Transactional(readOnly = true)
	public Iterable<UserType> findAll() {
		return UserTypeRepository.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Page<UserType> findAll(Pageable pageable) {
		return UserTypeRepository.findAll(pageable);
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<UserType> findById(Long id) {
		return UserTypeRepository.findById(id);
	}

	@Override
	@Transactional
	public void deleteByIdUserType(Long id) {
		UserTypeRepository.deleteById(id);
	}

	
}
