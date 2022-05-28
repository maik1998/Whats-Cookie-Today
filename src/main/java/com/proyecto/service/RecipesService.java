package com.proyecto.service;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.proyecto.model.Recipes;

public interface RecipesService {

	public Recipes saveRecipes (Recipes recipes);
	
	public Iterable<Recipes> findAll();
	
	public Page <Recipes> findAll(Pageable pageable);
	
	public Optional <Recipes> findById(Long id);
	
	public void deleteByIdRecipes (Long id);

	public void delete(Recipes recipes);
	
}
