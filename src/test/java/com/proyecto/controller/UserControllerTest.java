package com.proyecto.controller;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Map;
import java.util.Optional;

import javax.persistence.EntityManager;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;

import com.proyecto.model.Inventory;
import com.proyecto.model.User;
import com.proyecto.model.UserType;
import com.proyecto.repository.UserRepository;
import com.proyecto.service.UserServiceImpl;
import com.proyecto.utils.JWTUtil;

class UserControllerTest {

	
	UserRepository usrRepositoryMock= Mockito.mock(UserRepository.class);
	
	@Autowired
	UserServiceImpl usrService= new UserServiceImpl();
	
	@Autowired
	UserController usrController= new UserController();
	
	@Autowired
	JWTUtil jwtUtil= new JWTUtil();
	
	
	
	@BeforeEach
	void setUp() {
		usrService.setUserRepository(usrRepositoryMock);
		usrController.setUserService(usrService);
		usrController.setJwtUtil(jwtUtil);
		jwtUtil.setKey("ghk45jgherogho834go3h4g");
		jwtUtil.setIssuer("Main");
		jwtUtil.setTtlMillis(86400000);
		
		User usrMock= new User();
		usrMock.setIdUser(1);
		usrMock.setEmail("juanito@correo");
		usrMock.setName("Juanito Alimaña");
		usrMock.setPassword("123");
		usrMock.setPhone(7203580);
		usrMock.setTheInventory(new ArrayList<Inventory>());
		usrMock.setUserType(new UserType());
		String token= jwtUtil.create(String.valueOf(usrMock.getIdUser()), usrMock.getEmail());
		
		 Mockito.when(usrController.createUser(usrMock,token)).thenReturn(usrMock);
		 Mockito.when(usrRepositoryMock.findById((long) 1)).thenReturn(Optional.of(usrMock));
		 Mockito.when(usrRepositoryMock.save(usrMock)).thenReturn(usrMock);
	}

	
	@Test
	void testCreateUser() {
		User usrExpected= new User();
		usrExpected.setIdUser(1);
		usrExpected.setEmail("juanito@correo");
		usrExpected.setName("Juanito Alimaña");
		usrExpected.setPassword("123");
		usrExpected.setPhone(7203580);
		usrExpected.setTheInventory(new ArrayList<Inventory>());
		usrExpected.setUserType(new UserType());
		String token= jwtUtil.create(String.valueOf(usrExpected.getIdUser()), usrExpected.getEmail());
		
		User usrCreated= usrController.createUser(usrExpected, token);
		assertEquals(usrExpected, usrCreated);
	}

	@Test
	void testUpdateUser() {
		User usrExpected= new User();
		usrExpected.setIdUser(1);
		usrExpected.setEmail("juanito@correo");
		usrExpected.setName("Juanito Alimaña Táctico");
		usrExpected.setPassword("123");
		usrExpected.setPhone(7203580);
		usrExpected.setTheInventory(new ArrayList<Inventory>());
		usrExpected.setUserType(new UserType());
		String token= jwtUtil.create(String.valueOf(usrExpected.getIdUser()), usrExpected.getEmail());
		
		ResponseEntity<User> controllerResponse;
		controllerResponse= usrController.updateUser((long) 1, usrExpected,token);
		
		 assertEquals(200, controllerResponse.getStatusCodeValue());
	}

	@Test
	void testDeleteUser() {
		ResponseEntity<Map<String, Boolean>> controllerResponse;
		String token= jwtUtil.create("1", "juanito@correo");
		controllerResponse= usrController.deleteUser((long) 1,token);
		
		 assertEquals(200, controllerResponse.getStatusCodeValue());
	}
}


