package com.proyecto.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.proyecto.model.UserType;
import com.proyecto.service.UserTypeService;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:3000")
public class UserTypeController {

	@Autowired(required = true)
	private UserTypeService userTypeService;

	/**
	 * Obtener todos los tipos de usuarios
	 * 
	 * @return todos los tipos de usuarios
	 */
	@GetMapping("/listTypeUser")
	public List<UserType> getAllTypeUser() {
		return (List<UserType>) userTypeService.findAll();
	}

	/**
	 * Añadir Tipos de usuario
	 */
	@RequestMapping(method = RequestMethod.POST)
	public UserType createUserType(@RequestBody UserType userType) {
		return userTypeService.saveUserType(userType);
	}

}
