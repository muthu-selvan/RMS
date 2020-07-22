package com.restuarant.rest.webservices.restuarant.order.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.restuarant.rest.webservices.restuarant.constants.ReturnResultStatus;
import com.restuarant.rest.webservices.restuarant.modal.Order;
import com.restuarant.rest.webservices.restuarant.returnresult.ReturnResult;
import com.restuarant.rest.webservices.restuarant.services.OrderService;

@Component
public class OrderServiceImpl implements OrderService {

	@Autowired OrderJPARepository repository;
	
	@Override
	public ReturnResult addOrder(Order order) {
		ReturnResult result = new ReturnResult(ReturnResultStatus.SUCCESS);
		try {
			repository.save(order);
			result = new ReturnResult(ReturnResultStatus.SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			result = new ReturnResult(ReturnResultStatus.ERROR, "Internal Server Error");
		}
		return result;
	}

	@Override
	public List<Order> getAllOrders() {
		// TODO Auto-generated method stub
		return repository.findAll();
	}

	@Override
	public ReturnResult deleteOrder(int billNo) {
		ReturnResult result = new ReturnResult(ReturnResultStatus.SUCCESS);
		try {
			Order order = getOrderByBillNoId(billNo);
			if(order == null) {
				result = new ReturnResult(ReturnResultStatus.ERROR, "Order Not Found for Bill No "+billNo);
				return result;
			}
			repository.delete(order);
			result = new ReturnResult(ReturnResultStatus.SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			result = new ReturnResult(ReturnResultStatus.ERROR, "Internal Server Error");
		}
		return result;
	}

	@Override
	public ReturnResult updateOrder(Order order) {
		ReturnResult result = new ReturnResult(ReturnResultStatus.SUCCESS);
		try {
			result = deleteOrder(order.getBillNo());
			if(!result.getStatus().equals(ReturnResultStatus.SUCCESS)) {
				return result;
			}
			result = addOrder(order);
			if(!result.getStatus().equals(ReturnResultStatus.SUCCESS)) {
				return result;
			}
			result = new ReturnResult(ReturnResultStatus.SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			result = new ReturnResult(ReturnResultStatus.ERROR, "Internal Server Error");
		}
		return result;
	}

	@Override
	public Order getOrderByBillNoId(int billNo) {
		final Optional<Order> OrderOptional = repository.findByBillNo(billNo);
		if(OrderOptional != null && !OrderOptional.isEmpty()) {
			return OrderOptional.get();
		}
		return null;
	}

}
