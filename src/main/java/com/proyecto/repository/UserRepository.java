package com.proyecto.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.stereotype.Repository;

import com.proyecto.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	Optional <User> findByEmail(String email);
	
	@Query(value="SELECT * from user where email= :email AND password= :password", nativeQuery=true)
	List<User> autenticateUser(String email, String password);

}
