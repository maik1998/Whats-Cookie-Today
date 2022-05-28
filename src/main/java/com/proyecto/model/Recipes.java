package com.proyecto.model;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "recipes")
public class Recipes implements Serializable{
	
	private static final long serialVersionUID = 8657895498171212891L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name= "idRecipes", unique = true)
	private long idRecipes;

	@Column(name= "nameRecipes", length = 200, nullable = true)
	private String nameRecipes;
	
	@Column(name = "description", length = 8000, nullable = true)
	private String description;
	
	@Column(name = "category", length = 200, nullable = true)
	private String theCategory;

	
	
	public long getIdRecipes() {
		return idRecipes;
	}

	public void setIdRecipes(long idRecipes) {
		this.idRecipes = idRecipes;
	}

	public String getNameRecipes() {
		return nameRecipes;
	}

	public void setNameRecipes(String nameRecipes) {
		this.nameRecipes = nameRecipes;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getTheCategory() {
		return theCategory;
	}

	public void setTheCategory(String theCategory) {
		this.theCategory = theCategory;
	}

	@Override
	public int hashCode() {
		return Objects.hash(idRecipes);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Recipes other = (Recipes) obj;
		return idRecipes == other.idRecipes;
	}
	
	
	
}
