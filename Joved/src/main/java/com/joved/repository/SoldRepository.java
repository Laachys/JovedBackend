package com.joved.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.joved.entity.Favorite;
import com.joved.entity.Product;
import com.joved.entity.Sold;
import com.joved.interfaces.BuyProduct;

@Repository
public interface SoldRepository  extends JpaRepository<Sold, Integer> , CrudRepository<Sold, Integer>{

	Sold save(BuyProduct product);
	 List<Sold> findByProduct(String product);
}
