package com.proyecto.controller;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Map;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import com.proyecto.model.Ingredients;
import com.proyecto.model.Inventory;
import com.proyecto.model.User;
import com.proyecto.repository.IngredientsRepository;
import com.proyecto.service.IngredientsServiceImpl;


class IngredientsControllerTest {

	IngredientsRepository ingRepositoryMock= Mockito.mock(IngredientsRepository.class);
	
	@Autowired
	IngredientsServiceImpl ingService= new IngredientsServiceImpl();
	

	@Autowired
	IngredientsController ingController= new IngredientsController();
	
	 @BeforeEach
	 void setUp() {
		 ingService.setIngredientsRepository(ingRepositoryMock);
		 ingController.setIngredientsService(ingService);
		 
		 Ingredients ingMock= new Ingredients();
		 ingMock.setDescription("Roja");
		 ingMock.setIdIngredients(2L);
		 ingMock.setName("Cebolla");
		 ingMock.setQuantity(1);
		 ingMock.setTheInventory(new Inventory());
		 ingMock.setTheUser(new User());
		 
		 
		 Mockito.when(ingController.createIngredients(ingMock)).thenReturn(ingMock);
		 Mockito.when(ingRepositoryMock.findById(2L)).thenReturn(Optional.of(ingMock));
		 Mockito.when(ingRepositoryMock.save(ingMock)).thenReturn(ingMock);

	 }
	
	@Test
	void testCreateIngredients() {
		
		Ingredients ingExpected= new Ingredients();
		 ingExpected.setDescription("Roja");
		 ingExpected.setIdIngredients(2L);
		 ingExpected.setName("Cebolla");
		 ingExpected.setQuantity(1);
		 ingExpected.setTheInventory(new Inventory());
		 ingExpected.setTheUser(new User());
		 
		Ingredients ingCreated= ingController.createIngredients(ingExpected);
		
		assertEquals(ingExpected, ingCreated);
	}

	@Test
	void testDeleteIngredients() {
		
		ResponseEntity<Map<String, Boolean>> controllerResponse;
		controllerResponse= ingController.deleteIngredients(2L);
	
		assertEquals(200,controllerResponse.getStatusCodeValue());
	}

	@Test
	void testUpdateIngredients() {
		
		Ingredients ingExpected= new Ingredients();
		 ingExpected.setDescription("Azul");
		 ingExpected.setIdIngredients(2L);
		 ingExpected.setName("Cebolla");
		 ingExpected.setQuantity(1);
		 ingExpected.setTheInventory(new Inventory());
		 ingExpected.setTheUser(new User());
		
		ResponseEntity<Ingredients> controllerResponse;
		controllerResponse= ingController.updateIngredients(2L, ingExpected);
	
		assertEquals(200,controllerResponse.getStatusCodeValue());
	}

}
