package com.proyecto.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.proyecto.model.Inventory;

@Repository
public interface InventoryRepository extends JpaRepository<Inventory, Long>{

	@Query(value="SELECT * from inventory where id_user= :idUser", nativeQuery=true)
	List<Inventory> findAllByIdUser(int idUser);
}
