package com.proyecto.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.proyecto.model.User;
import com.proyecto.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

	private final EntityManager entityManager;

	@Autowired
	public UserServiceImpl(EntityManager entityManager) {

		this.entityManager = entityManager;
	}
	
	public UserServiceImpl() {
		entityManager=null;
	}

	@Autowired
	private UserRepository userRepository;

	@Override
	@Transactional
	public User saveUser(User user) {
		return userRepository.save(user);
	}

	@Override
	@Transactional(readOnly = true)
	public Iterable<User> findAll() {
		return userRepository.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Page<User> findAll(Pageable pageable) {
		return userRepository.findAll(pageable);
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<User> findById(Long id) {
		return userRepository.findById(id);
	}

	@Override
	@Transactional
	public void deleteByIdUser(Long id) {
		userRepository.deleteById(id);
	}

	@Override
	@Transactional
	public void delete(User user) {
		userRepository.delete(user);

	}

	@Override
	@Transactional(readOnly = true)
	public Optional<User> findByEmailUser(String email) {
		return userRepository.findByEmail(email);
	}

	@Override
	public User autenticateUser(String email, String password) {
		
		System.out.println(password);
		int numero = 0;
		List <User> lista = new ArrayList<User>();
		try {
			lista = userRepository.autenticateUser(email, password);
		}
		catch (Exception ex) {
			System.out.println(ex.toString());
			numero = -1;
		}
		if(lista.isEmpty()) {
			return null;
			
		}
		
		String emailLogged= lista.get(0).getEmail();
		String passwordLogged= lista.get(0).getPassword();
		if(emailLogged.equals(emailLogged) && passwordLogged.equals(password)){
			return lista.get(0);
		}
		return null;
	}

	public UserRepository getUserRepository() {
		return userRepository;
	}

	public void setUserRepository(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	/*@Override
	public List<Object> autenticateUser(String email, String password) {

		System.out.println(password);
		int numero = 0;
		List<Object> lista = new ArrayList<Object>();
		try {

			StoredProcedureQuery storedProcedureQuery = entityManager
					.createStoredProcedureQuery("bd_wct.autenticateUser");

			// Registrar los parámetros de entrada y salida
			storedProcedureQuery.registerStoredProcedureParameter("parametro1", String.class, ParameterMode.IN);
			storedProcedureQuery.registerStoredProcedureParameter("parametro2", String.class, ParameterMode.IN);

			// Configuramos el valor de entrada
			storedProcedureQuery.setParameter("parametro1", email);
			storedProcedureQuery.setParameter("parametro2", password);

			// Realizamos la llamada al procedimiento
			storedProcedureQuery.execute();

			lista = storedProcedureQuery.getResultList();

		} catch (Exception ex) {
			System.out.println(ex.toString());
			numero = -1;
		}
		return lista;
	}*/
	
	

}
