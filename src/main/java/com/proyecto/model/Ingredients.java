package com.proyecto.model;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table(name = "ingredients")
public class Ingredients implements Serializable {

	private static final long serialVersionUID = 833462822904873945L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idIngredients", unique = true)
	private long idIngredients;

	@Column(name = "name", length = 200, nullable = false)
	private String name;

	@Column(name = "description", length = 500, nullable = true)
	private String description;

	@Column(name = "quantity", length = 50, nullable = false)
	private int quantity;

	@ManyToOne
	@JoinColumn(name="idInventory")
	private Inventory theInventory;
	
	@ManyToOne
	@JoinColumn(name="idUser")
	private User theUser;

	public long getIdIngredients() {
		return idIngredients;
	}

	public void setIdIngredients(long idIngredients) {
		this.idIngredients = idIngredients;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Inventory getTheInventory() {
		return theInventory;
	}

	public void setTheInventory(Inventory theInventory) {
		this.theInventory = theInventory;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public User getTheUser() {
		return theUser;
	}

	public void setTheUser(User theUser) {
		this.theUser = theUser;
	}

	@Override
	public int hashCode() {
		return Objects.hash(idIngredients);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Ingredients other = (Ingredients) obj;
		return idIngredients == other.idIngredients;
	}

}
