package com.proyecto.controller;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
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
import com.proyecto.repository.InventoryRepository;
import com.proyecto.service.InventoryServiceImpl;

class InventoryControllerTest {


	InventoryRepository invRepositoryMock= Mockito.mock(InventoryRepository.class);
	
	@Autowired
	InventoryServiceImpl invService= new InventoryServiceImpl();
	

	@Autowired
	InventoryController invController= new InventoryController();
	
	
	 @BeforeEach
	 void setUp() {
		 invService.setInventoryRepository(invRepositoryMock);
		 invController.setInventoryService(invService);		 
		 
		 Inventory invMock= new Inventory();
		 invMock.setIdInventory(1L);
		 invMock.setName("Inventario Mock");
		 invMock.setTheIngredients(new ArrayList<Ingredients>());
		 invMock.setTheUser(new User());
		 
		 
		 Mockito.when(invController.createInventory(invMock)).thenReturn(invMock);
		 Mockito.when(invRepositoryMock.findById(1L)).thenReturn(Optional.of(invMock));
		 Mockito.when(invRepositoryMock.save(invMock)).thenReturn(invMock);

	 }
	
	@Test
	void testCreateInventory() {
		
		Inventory invExpected= new Inventory();
		 invExpected.setIdInventory(1);
		 invExpected.setName("Inventario Mock");
		 invExpected.setTheIngredients(new ArrayList<Ingredients>());
		 invExpected.setTheUser(new User());
		 
		Inventory invCreated= invController.createInventory(invExpected);
		 
		
		assertEquals(invExpected, invCreated);
	}

	@Test
	void testUpdateInventory() {
		
		Inventory invExpected= new Inventory();
		 invExpected.setIdInventory(1);
		 invExpected.setName("Inventario Mock Updated");
		 invExpected.setTheIngredients(new ArrayList<Ingredients>());
		 invExpected.setTheUser(new User());
		 
		 ResponseEntity<Inventory> controllerResponse;
		 controllerResponse= invController.updateInventory(1L, invExpected);
		 
		 assertEquals(200, controllerResponse.getStatusCodeValue());
		 
	
	}

	@Test
	void testDeleteInventory() {
		
		ResponseEntity<Map<String, Boolean>> controllerResponse;
		controllerResponse= invController.deleteInventory(1L);
	
		assertEquals(200,controllerResponse.getStatusCodeValue());
	}

}
