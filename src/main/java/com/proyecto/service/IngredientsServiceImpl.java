package com.proyecto.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.proyecto.model.Ingredients;
import com.proyecto.repository.IngredientsRepository;

@Service
public class IngredientsServiceImpl implements IngredientsService{

	@Autowired
	private IngredientsRepository IngredientsRepository;
	
	@Override
	@Transactional
	public Ingredients saveIngredients(Ingredients Ingredients) {
		return IngredientsRepository.save(Ingredients);
	}

	@Override
	@Transactional(readOnly = true)
	public Iterable<Ingredients> findAll() {
		return IngredientsRepository.findAll();
	}
	
	@Override
	public List<Ingredients> findAllByIdUser(int idUser) {
		int numero = 0;
		List<Ingredients> lista = new ArrayList<Ingredients>();
		try {
			lista = IngredientsRepository.findAllByIdUser(idUser);
		} catch (Exception ex) {
			System.out.println(ex.toString());
			numero = -1;
		}
		if (lista.isEmpty()) {
			return lista;
		}
		return lista;
	}


	@Override
	@Transactional(readOnly = true)
	public Page<Ingredients> findAll(Pageable pageable) {
		return IngredientsRepository.findAll(pageable);
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<Ingredients> findById(Long id) {
		return IngredientsRepository.findById(id);
	}

	@Override
	@Transactional
	public void deleteByIdIngredients(Long id) {
		IngredientsRepository.deleteById(id);
	}

	@Override
	@Transactional
	public void delete(Ingredients ingredients) {
		IngredientsRepository.delete(ingredients);
		
	}

	public IngredientsRepository getIngredientsRepository() {
		return IngredientsRepository;
	}

	public void setIngredientsRepository(IngredientsRepository ingredientsRepository) {
		IngredientsRepository = ingredientsRepository;
	}


	
}
