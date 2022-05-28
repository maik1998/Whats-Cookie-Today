package com.proyecto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.proyecto.controller.UserTypeController;
import com.proyecto.model.UserType;

@SpringBootApplication
public class SpringbootWctApplication implements CommandLineRunner{

	@Autowired UserTypeController userTypeController;
	public static void main(String[] args) {
		SpringApplication.run(SpringbootWctApplication.class, args);
	}

	@Override
	public void run (String ... args) throws Exception{
		UserType Administrator = new UserType(1, "Administrador");
		UserType User = new UserType(2,"Usuario");
		userTypeController.createUserType(Administrator);
		userTypeController.createUserType(User);
		
	
	}
}
