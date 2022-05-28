package com.proyecto.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.proyecto.model.User;

public interface UserService {

	public User saveUser (User user);
	
	public Iterable<User> findAll();
	
	public Page <User> findAll(Pageable pageable);
	
	public Optional <User> findById(Long id);
	
	public void deleteByIdUser (Long id);

	public void delete(User user);
	
	public User autenticateUser(String email, String password);

	public Optional<User> findByEmailUser(String email);
	
}
