package com.joved.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.joved.entity.User;
import com.joved.interfaces.RegisterUser;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> , CrudRepository<User, Integer> {


	User findByUsername(String username);
	User save(RegisterUser register);
	
}
