package com.proyecto.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.proyecto.model.Ingredients;


public interface IngredientsService {

	public Ingredients saveIngredients (Ingredients Ingredients);
	
	public Iterable<Ingredients> findAll();
	
	public Page <Ingredients> findAll(Pageable pageable);
	
	public Optional <Ingredients> findById(Long id);
	
	public void deleteByIdIngredients (Long id);

	public void delete(Ingredients ingredients);

	public List<Ingredients> findAllByIdUser(int id);
	
}
