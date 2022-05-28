package com.proyecto.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.proyecto.exception.ResourceNotFoundException;
import com.proyecto.model.Ingredients;
import com.proyecto.service.IngredientsService;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:3000")
public class IngredientsController {

	@Autowired(required = true)
	private IngredientsService ingredientsService;

	/**
	 * Obtener todos los ingredientes
	 * 
	 * @return todos los ingredientes
	 */
	@GetMapping("/listIngredient")
	public List<Ingredients> getAllIngredients() {
		return (List<Ingredients>) ingredientsService.findAll();
	}
	
	/**
	 * Obtiene todos los ingredientes dado el usuario asociado
	 * @param id
	 * @return
	 */
	@GetMapping("/listIngredientClient/{id}")
	public List<Ingredients> getAllInventoryByIdUser(@PathVariable int id) {
		System.out.println(id);
		return ingredientsService.findAllByIdUser(id);
	}

	/**
	 * Añadir Ingredientes rest API
	 */
	@PostMapping("/addIngredient")
	public Ingredients createIngredients(@RequestBody Ingredients ingredients) {
		return ingredientsService.saveIngredients(ingredients);
	}

	/**
	 * Obtener ingredientes por id rest API
	 */
	@GetMapping("/getIngredient/{id}")
	public ResponseEntity<Ingredients> getIngredientsById(@PathVariable Long id) {
		Ingredients ingredients = ingredientsService.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("No existe el ingrediente con el id:" + id));
		return ResponseEntity.ok(ingredients);

	}

	/**
	 * Actualizar ingredientes rest API
	 */
	@PutMapping("/updateIngredient/{id}")
	public ResponseEntity<Ingredients> updateIngredients(@PathVariable Long id, @RequestBody Ingredients ingredientsDetails) {

		Ingredients ingredients = ingredientsService.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("No existe el ingrediente con el id:" + id));

		ingredients.setName(ingredientsDetails.getName());
		ingredients.setDescription(ingredientsDetails.getDescription());
		ingredients.setQuantity(ingredientsDetails.getQuantity());
		ingredients.setTheInventory(ingredientsDetails.getTheInventory());

		Ingredients updateIngredients = ingredientsService.saveIngredients(ingredients);

		return ResponseEntity.ok(updateIngredients);

	}

	/**
	 * Eliminar ingrediente rest API
	 */
	@DeleteMapping("/deleteIngredient/{id}")
	public ResponseEntity<Map<String, Boolean>> deleteIngredients(@PathVariable Long id) {

		Ingredients ingredients = ingredientsService.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("No existe el ingrediente con el id:" + id));

		ingredientsService.delete(ingredients);
		Map<String, Boolean> response = new HashMap<>();
		response.put("Eliminado", Boolean.TRUE);
		return ResponseEntity.ok(response);

	}

	public IngredientsService getIngredientsService() {
		return ingredientsService;
	}

	public void setIngredientsService(IngredientsService ingredientsService) {
		this.ingredientsService = ingredientsService;
	}
}
