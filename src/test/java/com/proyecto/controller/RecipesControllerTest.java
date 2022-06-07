package com.proyecto.controller;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Map;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import com.proyecto.model.Recipes;
import com.proyecto.repository.RecipesRepository;
import com.proyecto.service.RecipesServiceImpl;

class RecipesControllerTest {
	
	RecipesRepository recRepositoryMock= Mockito.mock(RecipesRepository.class);
	
	@Autowired
	RecipesServiceImpl recService= new RecipesServiceImpl();
	
	@Autowired
	RecipesController recController= new RecipesController();
	
	
	@BeforeEach
	void setUp() {
		recService.setRecipesRepository(recRepositoryMock);
		recController.setRecipesService(recService);
		
		Recipes recMock= new Recipes();
		recMock.setDescription("Hornear papas y agregar queso");
		recMock.setIdRecipes(1L);
		recMock.setNameRecipes("Papa gratinada");
		recMock.setTheCategory("Comida Rápida");
		
		
		 Mockito.when(recController.createRecipes(recMock)).thenReturn(recMock);
		 Mockito.when(recRepositoryMock.findById(1L)).thenReturn(Optional.of(recMock));
		 Mockito.when(recRepositoryMock.save(recMock)).thenReturn(recMock);
	}

	@Test
	void testCreateRecipes() {
		
		Recipes recExpected= new Recipes();
		recExpected.setDescription("Hornear papas y agregar queso");
		recExpected.setIdRecipes(1L);
		recExpected.setNameRecipes("Papa gratinada");
		recExpected.setTheCategory("Comida Rápida");
		
		Recipes recCreated= recController.createRecipes(recExpected);
		assertEquals(recExpected, recCreated);
	}

	@Test
	void testUpdateRecipes() {
		Recipes recExpected= new Recipes();
		recExpected.setDescription("Hornear papas y agregar queso + Maní");
		recExpected.setIdRecipes(1L);
		recExpected.setNameRecipes("Papa gratinada");
		recExpected.setTheCategory("Comida Rápida");
		
		ResponseEntity<Recipes> controllerResponse;
		controllerResponse= recController.updateRecipes(1L, recExpected);
		
		 assertEquals(200, controllerResponse.getStatusCodeValue());
		
	}

	@Test
	void testDeleteRecipes() {
		
		ResponseEntity<Map<String, Boolean>> controllerResponse;
		controllerResponse= recController.deleteRecipes(1L);
		
		 assertEquals(200, controllerResponse.getStatusCodeValue());
	}

}
