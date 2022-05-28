package com.proyecto.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.proyecto.model.Recipes;

@Repository
public interface RecipesRepository extends JpaRepository<Recipes, Long>{

}
