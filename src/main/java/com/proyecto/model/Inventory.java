package com.proyecto.model;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "inventory")
public class Inventory implements Serializable{

	private static final long serialVersionUID = 97455479468594946L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idInventory", unique = true)
	private long idInventory;

	@Column(name = "name", length = 200, nullable = false)
	private String name;

	@JsonIgnore
	@OneToMany(mappedBy="theInventory")
	private List<Ingredients> theIngredients;
	
	@ManyToOne
	@JoinColumn(name="idUser")
	private User theUser;

	public long getIdInventory() {
		return idInventory;
	}

	public void setIdInventory(long idInventory) {
		this.idInventory = idInventory;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Ingredients> getTheIngredients() {
		return theIngredients;
	}

	public void setTheIngredients(List<Ingredients> theIngredients) {
		this.theIngredients = theIngredients;
	}

	public User getTheUser() {
		return theUser;
	}

	public void setTheUser(User theUser) {
		this.theUser = theUser;
	}

	@Override
	public int hashCode() {
		return Objects.hash(idInventory);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Inventory other = (Inventory) obj;
		return idInventory == other.idInventory;
	}

	
}
