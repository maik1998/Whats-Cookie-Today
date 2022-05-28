package com.proyecto.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.proyecto.model.Inventory;
import com.proyecto.model.User;

public interface InventoryService {

	public Inventory saveInventory (Inventory inventory);
	
	public Iterable<Inventory> findAll();
	
	public Page <Inventory> findAll(Pageable pageable);
	
	public Optional <Inventory> findById(Long id);
	
	public void deleteByIdInventory (Long id);

	public void delete(Inventory inventory);

	public List<Inventory> findAllByIdUser(int idUser);
	
}
