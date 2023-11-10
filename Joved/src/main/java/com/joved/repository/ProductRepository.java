package com.joved.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import com.joved.entity.Product;
import com.joved.interfaces.RegisterProduct;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> , CrudRepository<Product, Integer> {

	Product save(RegisterProduct registerproduct);
	List<Product> findByCategoryIs(Integer category);
	
	 List<Product> findByCategoryAndActiveNot(Integer category, String activo);
	 List<Product> findByActive(String activo);
	 List<Product> findByUser(String user);
	 
	 List<Product> findByUserAndActiveNot(String id , String activo);
}
