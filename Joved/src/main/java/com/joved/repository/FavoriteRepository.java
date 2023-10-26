package com.joved.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.joved.entity.Favorite;
import com.joved.interfaces.NewFavorite;

@Repository
public interface FavoriteRepository extends JpaRepository<Favorite, Integer> , CrudRepository<Favorite, Integer> {

	Favorite save(NewFavorite newfavorite);
	List<Favorite> findAllByUser(String id_user);
	
	Favorite findByUserAndProduct(String id_user, String id_product);
	
	long removeByUserAndProduct(String id_user, String id_product);
}
