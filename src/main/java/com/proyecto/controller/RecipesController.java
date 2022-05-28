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
import com.proyecto.model.Recipes;
import com.proyecto.service.RecipesService;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:3000")
public class RecipesController {
	@Autowired(required = true)
	private RecipesService recipesService;

	/**
	 * Obtener todas los recetas
	 * 
	 * @return todas los recetas
	 */
	@GetMapping("/listRecipe")
	public List<Recipes> getAllRecipes() {
		return (List<Recipes>) recipesService.findAll();
	}

	/**
	 * Añadir recetas rest API
	 */
	@PostMapping("/addRecipe")
	public Recipes createRecipes(@RequestBody Recipes recipes) {
		return recipesService.saveRecipes(recipes);
	}

	/**
	 * Obtener receta por id rest API
	 */
	@GetMapping("/getRecipe/{id}")
	public ResponseEntity<Recipes> getRecipesById(@PathVariable Long id) {
		Recipes recipes = recipesService.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("No existe la receta con el id:" + id));
		return ResponseEntity.ok(recipes);

	}

	/**
	 * Actualizar receta rest API
	 */
	@PutMapping("/updateRecipe/{id}")
	public ResponseEntity<Recipes> updateRecipes(@PathVariable Long id, @RequestBody Recipes recipesDetails) {

		Recipes recipes = recipesService.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("No existe la receta con el id:" + id));

		recipes.setNameRecipes(recipesDetails.getNameRecipes());
		recipes.setDescription(recipesDetails.getDescription());
		recipes.setTheCategory(recipesDetails.getTheCategory());

		Recipes updateRecipes = recipesService.saveRecipes(recipes);

		return ResponseEntity.ok(updateRecipes);

	}

	/**
	 * Eliminar receta rest API
	 */
	@DeleteMapping("/deleteRecipe/{id}")
	public ResponseEntity<Map<String, Boolean>> deleteRecipes(@PathVariable Long id) {

		Recipes recipes = recipesService.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("No existe la receta con el id:" + id));

		recipesService.delete(recipes);
		Map<String, Boolean> response = new HashMap<>();
		response.put("Eliminado", Boolean.TRUE);
		return ResponseEntity.ok(response);

	}
}

