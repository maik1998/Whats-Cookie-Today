package com.proyecto.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.proyecto.model.Ingredients;

@Repository
public interface IngredientsRepository extends JpaRepository<Ingredients, Long>{

	@Query(value="SELECT * from ingredients where id_user= :idUser", nativeQuery=true)
	List<Ingredients> findAllByIdUser(int idUser);
}
