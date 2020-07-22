package com.restuarant.rest.webservices.restuarant.product.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.restuarant.rest.webservices.restuarant.modal.Product;

@Repository
public interface ProductJPARepository extends JpaRepository<Product, Integer> {
	
	public Optional<Product> findByRestId(int restId);

}
