package com.restuarant.rest.webservices.restuarant.order;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.restuarant.rest.webservices.restuarant.modal.Order;
import com.restuarant.rest.webservices.restuarant.returnresult.ReturnResult;

@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*")
@RestController
public class OrderController {
	
	@Autowired OrderControllerHelper helper;

	@GetMapping(path = "/RMS/getAllOrders")
	public List<Order> getAllOrders() {
		return helper.getAllOrders();
	}
	
	@DeleteMapping(path = "/RMS/deleteOrder/{orderId}")
	public ReturnResult deleteProduct(@PathVariable("orderId") int orderId) {
		return helper.deleteOrder(orderId);
	}
	
	@PostMapping(path = "/RMS/addOrder/{orderId}", consumes = "application/json")
	public ReturnResult addProduct(@PathVariable("orderId") String orderId, @RequestBody Order order) {
		return helper.addOrder(order);
	}
}
