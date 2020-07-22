package com.restuarant.rest.webservices.restuarant.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.restuarant.rest.webservices.restuarant.modal.Restuarant;

@Repository
public interface RestuarantJPARepository  extends JpaRepository<Restuarant,Integer> {
	
     public Optional<Restuarant> findByRestId(int restId);
}
