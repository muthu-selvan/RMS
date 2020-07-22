package com.restuarant.rest.webservices.restuarant.order.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.restuarant.rest.webservices.restuarant.modal.Order;

public interface OrderJPARepository extends JpaRepository<Order, Integer> {
	
	public Optional<Order> findByBillNo(int billNo);

}
