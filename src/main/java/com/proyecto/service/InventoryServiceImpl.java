package com.proyecto.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.proyecto.model.Inventory;
import com.proyecto.repository.InventoryRepository;

@Service
public class InventoryServiceImpl implements InventoryService {

	@Autowired
	private InventoryRepository InventoryRepository;

	@Override
	@Transactional
	public Inventory saveInventory(Inventory inventory) {
		return InventoryRepository.save(inventory);
	}

	@Override
	@Transactional(readOnly = true)
	public Iterable<Inventory> findAll() {
		return InventoryRepository.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Page<Inventory> findAll(Pageable pageable) {
		return InventoryRepository.findAll(pageable);
	}

	@Override
	public List<Inventory> findAllByIdUser(int idUser) {
		int numero = 0;
		List<Inventory> lista = new ArrayList<Inventory>();
		try {
			lista = InventoryRepository.findAllByIdUser(idUser);
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
	public Optional<Inventory> findById(Long id) {
		return InventoryRepository.findById(id);
	}

	@Override
	@Transactional
	public void deleteByIdInventory(Long id) {
		InventoryRepository.deleteById(id);
	}

	@Override
	@Transactional
	public void delete(Inventory inventory) {
		InventoryRepository.delete(inventory);
	}

	public InventoryRepository getInventoryRepository() {
		return InventoryRepository;
	}

	public void setInventoryRepository(InventoryRepository inventoryRepository) {
		InventoryRepository = inventoryRepository;
	}

}
