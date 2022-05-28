package com.proyecto.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.proyecto.model.Recipes;
import com.proyecto.repository.RecipesRepository;

@Service
public class RecipesServiceImpl implements RecipesService {

	@Autowired
	private RecipesRepository RecipesRepository;

	@Override
	@Transactional
	public Recipes saveRecipes(Recipes recipes) {
		return RecipesRepository.save(recipes);
	}

	@Override
	@Transactional(readOnly = true)
	public Iterable<Recipes> findAll() {
		return RecipesRepository.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Page<Recipes> findAll(Pageable pageable) {
		return RecipesRepository.findAll(pageable);
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<Recipes> findById(Long id) {
		return RecipesRepository.findById(id);
	}

	@Override
	@Transactional
	public void deleteByIdRecipes(Long id) {
		RecipesRepository.deleteById(id);
	}

	@Override
	@Transactional
	public void delete(Recipes recipes) {
		RecipesRepository.delete(recipes);

	}

}
