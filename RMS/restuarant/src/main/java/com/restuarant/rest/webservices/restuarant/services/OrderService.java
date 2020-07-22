package com.restuarant.rest.webservices.restuarant.services;

import java.util.List;

import com.restuarant.rest.webservices.restuarant.modal.Order;
import com.restuarant.rest.webservices.restuarant.returnresult.ReturnResult;

public interface OrderService {
	
	public ReturnResult addOrder(Order order);
	
	public List<Order> getAllOrders();
	
	public ReturnResult deleteOrder(int billNo);
	
	public ReturnResult updateOrder(Order order);
	
	public Order getOrderByBillNoId(int billNo);

}
