package com.proyecto.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.proyecto.model.UserType;

@Repository
public interface UserTypeRepository extends JpaRepository<UserType, Long> {

}
