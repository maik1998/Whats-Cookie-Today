package com.proyecto.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.proyecto.service.UserService;
import com.proyecto.utils.JWTUtil;
import com.proyecto.exception.ResourceNotFoundException;
import com.proyecto.model.User;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:3000")
public class UserController {

	@Autowired(required = true)
	private UserService userService;
	
	@Autowired
	private JWTUtil jwtUtil;
	
	
	private boolean validarToken(String Token) {
		String usuarioId= jwtUtil.getKey(Token);
		return usuarioId != null;
	}

	/**
	 * registrar un usuario
	 * 
	 * @param user
	 * @return
	 */
	@PostMapping("/registerUser")
	public boolean registerUser(@RequestBody User user) {
		boolean validar = false;
		if (user.getEmail() != "" && !userService.findByEmailUser(user.getEmail()).isPresent()) {
			if (userService.saveUser(user) != null) {
				validar = true;
				userService.saveUser(user);
			}

		}
		return validar;
	}

	/**
	 * Autenticar Usuario
	 * 
	 * @param user
	 * @return
	 */
	@PostMapping("/login")
	public Object autenticateUser(@RequestBody User user) {

		User userLogged=userService.autenticateUser(user.getEmail(), user.getPassword());
		List<Object> loginUser= new ArrayList<>();
		
		if(userLogged != null) {
			String token= jwtUtil.create(String.valueOf(user.getIdUser()), user.getEmail());
			loginUser.add(userLogged.getIdUser());
			loginUser.add(userLogged.getEmail());
			loginUser.add(userLogged.getName());
			loginUser.add(userLogged.getPassword());
			loginUser.add(userLogged.getPhone());
			loginUser.add(userLogged.getUserType().getId());
			loginUser.add(userLogged.getUserType().getName());
			loginUser.add(token);
			return loginUser;
		}
		
		return ResponseEntity.status(HttpStatus.UNAUTHORIZED);
	}

	/**
	 * Obtener todos los usuarios
	 * 
	 * @return todos los usuarios
	 */
	@GetMapping("/listUser")
	public List<User> getAllUser(@RequestHeader(value="Authorization")String Token) {
		
		if(!validarToken(Token)) {
			return null;
		}
		return (List<User>) userService.findAll();
	}

	/**
	 * Añadir Usuarios rest API
	 */
	@PostMapping("/addUser")
	public User createUser(@RequestBody User user, @RequestHeader(value="Authorization")String Token) {
		if(!validarToken(Token)) {
			return null;
		}
		return userService.saveUser(user);
	}

	/**
	 * Obtener usuarios por id rest API
	 */
	@GetMapping("/getUser/{id}")
	public ResponseEntity<User> getUserById(@PathVariable Long id , @RequestHeader(value="Authorization")String Token) {
		
		if(!validarToken(Token)) {
			return null;
		}
		User user = userService.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("No existe el usuario con el id:" + id));
		return ResponseEntity.ok(user);

	}

	/**
	 * Actualizar usuarios rest API
	 */
	@PutMapping("/updateUser/{id}")
	public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody User userDetails, @RequestHeader(value="Authorization")String Token) {

		if(!validarToken(Token)) {
			return null;
		}
		User user = userService.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("No existe el usuario con el id:" + id));

		user.setName(userDetails.getName());
		user.setEmail(userDetails.getEmail());
		user.setPassword(userDetails.getPassword());
		user.setPhone(userDetails.getPhone());
		user.setUserType(userDetails.getUserType());

		User updateUser = userService.saveUser(user);

		return ResponseEntity.ok(updateUser);

	}

	/**
	 * Eliminar usuario rest API
	 */
	@DeleteMapping("/deleteUser/{id}")
	public ResponseEntity<Map<String, Boolean>> deleteUser(@PathVariable Long id, @RequestHeader(value="Authorization")String Token) {

		if(!validarToken(Token)) {
			return null;
		}
		User user = userService.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("No existe el usuario con el id:" + id));

		userService.delete(user);
		Map<String, Boolean> response = new HashMap<>();
		response.put("Eliminado", Boolean.TRUE);
		return ResponseEntity.ok(response);

	}
}
