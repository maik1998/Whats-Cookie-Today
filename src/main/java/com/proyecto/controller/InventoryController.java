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
import com.proyecto.model.Inventory;
import com.proyecto.service.InventoryService;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:3000")
public class InventoryController {
	
	@Autowired(required = true)
	private InventoryService inventoryService;

	/**
	 * Obtener todos los inventario
	 * 
	 * @return todos los inventario
	 */
	@GetMapping("/listInventory")
	public List<Inventory> getAllInventory() {
		return (List<Inventory>) inventoryService.findAll();
	}
	
	
	/**
	 * Obtiene todos los inventarios dado el usuario asociado
	 * @param id
	 * @return
	 */
	@GetMapping("/listInventoryClient/{id}")
	public List<Inventory> getAllInventoryByIdUser(@PathVariable int id) {
		System.out.println(id);
		return inventoryService.findAllByIdUser(id);
	}

	/**
	 * Añadir Inventarios rest API
	 */
	@PostMapping("/addInventory")
	public Inventory createInventory(@RequestBody Inventory inventory) {
		return inventoryService.saveInventory(inventory);
	}

	/**
	 * Obtener inventario por id rest API
	 */
	@GetMapping("/getInventory/{id}")
	public ResponseEntity<Inventory> getInventoryById(@PathVariable Long id) {
		Inventory inventory = inventoryService.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("No existe el inventario con el id:" + id));
		return ResponseEntity.ok(inventory);

	}

	/**
	 * Actualizar inventario rest API
	 */
	@PutMapping("/updateInventory/{id}")
	public ResponseEntity<Inventory> updateInventory(@PathVariable Long id, @RequestBody Inventory inventoryDetails) {

		Inventory inventory = inventoryService.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("No existe el inventario con el id:" + id));

		inventory.setName(inventoryDetails.getName());

		Inventory updateInventory = inventoryService.saveInventory(inventory);

		return ResponseEntity.ok(updateInventory);

	}

	/**
	 * Eliminar inventario rest API
	 */
	@DeleteMapping("/deleteInventory/{id}")
	public ResponseEntity<Map<String, Boolean>> deleteInventory(@PathVariable Long id) {

		Inventory inventory = inventoryService.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("No existe el inventario con el id:" + id));

		inventoryService.delete(inventory);
		Map<String, Boolean> response = new HashMap<>();
		response.put("Eliminado", Boolean.TRUE);
		return ResponseEntity.ok(response);

	}
}

