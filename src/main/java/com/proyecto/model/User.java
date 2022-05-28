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
@Table(name = "user")
public class User implements Serializable {

	private static final long serialVersionUID = -8187479413291106473L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idUser", unique = true)
	private long idUser;

	@Column(name = "name", length = 200, nullable = false)
	private String name;

	@Column(name = "phone", length = 50, nullable = true)
	private int phone;

	@Column(name = "email", length = 200, nullable = false, unique = true)
	private String email;

	@Column(name = "password", length = 50, nullable = false)
	private String password;

	@JoinColumn(name = "userTypeId")
	@ManyToOne
	private UserType userType;
	
	@JsonIgnore
	@OneToMany(mappedBy="theUser")
	private List <Inventory> theInventory;
	
	@JsonIgnore
	@OneToMany(mappedBy="theUser")
	private List<Ingredients> theIngredients;

	public long getIdUser() {
		return idUser;
	}

	public void setIdUser(long idUser) {
		this.idUser = idUser;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getPhone() {
		return phone;
	}

	public void setPhone(int phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public UserType getUserType() {
		return userType;
	}

	public void setUserType(UserType userType) {
		this.userType = userType;
	}

	public List<Inventory> getTheInventory() {
		return theInventory;
	}

	public void setTheInventory(List<Inventory> theInventory) {
		this.theInventory = theInventory;
	}

	@Override
	public int hashCode() {
		return Objects.hash(idUser);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		return idUser == other.idUser;
	}

}
