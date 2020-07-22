package com.restuarant.rest.webservices.restuarant.order;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.restuarant.rest.webservices.restuarant.common.CommonUtil;
import com.restuarant.rest.webservices.restuarant.constants.RMSConstants;
import com.restuarant.rest.webservices.restuarant.constants.ReturnResultStatus;
import com.restuarant.rest.webservices.restuarant.modal.Order;
import com.restuarant.rest.webservices.restuarant.modal.Restuarant;
import com.restuarant.rest.webservices.restuarant.returnresult.ReturnResult;
import com.restuarant.rest.webservices.restuarant.services.OrderService;
import com.restuarant.rest.webservices.restuarant.services.RestuarantService;

@Component
public class OrderControllerHelper {
	
	@Autowired OrderService service;
	
	@Autowired RestuarantService restuarantService;
	
	public List<Order> getAllOrders() {
		return service.getAllOrders();
	}
	
	ReturnResult deleteOrder(final int orderId) {
		ReturnResult returnResult = new ReturnResult(ReturnResultStatus.SUCCESS);
		List<Order> selectedOrders = getOrderForOrderId(orderId);
		
		if(selectedOrders == null || selectedOrders.isEmpty()) {
			returnResult = new ReturnResult(ReturnResultStatus.ERROR, "Selected Order Not Found");
			return returnResult;
		}
		
		for(Order order : selectedOrders) {
			if(CommonUtil.isValid(order.getPaidStatus()) && order.getPaidStatus().equals(RMSConstants.NO.getValue())) {
				returnResult = new ReturnResult(ReturnResultStatus.ERROR, "Unpaid Order is Pending");
				return returnResult;
			}
		}
		
		return service.deleteOrder(orderId);
	}
	
	private  List<Order> getOrderForOrderId(final int orderId) {
		
		final List<Order> selectedOrders = new ArrayList<Order>();
		
		for(Order order : service.getAllOrders()) {
			if(order.getBillNo() == orderId) {
				selectedOrders.add(order);
			}
		}
		
		return selectedOrders;
	}
	
	ReturnResult addOrder(final Order order) {
		final ReturnResult returnResult = validate(order);
		if(returnResult.getStatus().equals(ReturnResultStatus.ERROR)) {
			return returnResult;
		}
		
		if(isRecordExists(order)) {
			service.updateOrder(order);
		}
		
		return service.addOrder(order);
	}
	
	private boolean isRecordExists(final Order order) {
		return (service.getOrderByBillNoId(order.getBillNo()) != null);
	}

	private ReturnResult validate(final Order order) {
		ReturnResult returnResult = new ReturnResult(ReturnResultStatus.SUCCESS);
		
		if(order.getRestId() == 0) {
			returnResult = new ReturnResult(ReturnResultStatus.ERROR, "Invalid Restuarant Id");
			return returnResult;
		}
		
		if(!CommonUtil.isValid(order.getRestName())) {
			returnResult = new ReturnResult(ReturnResultStatus.ERROR, "Invalid Restuarant Name");
			return returnResult;
		}
		
//		Validate Restuarant Id and Name
		final Restuarant restuarant = restuarantService.getRestuarantById(order.getRestId());
		if(restuarant == null) {
			returnResult = new ReturnResult(ReturnResultStatus.ERROR, "Restaurant "+order.getRestId()+" Not Found");
			return returnResult;
		}
		
		if(!restuarant.getRestName().equals(order.getRestName())) {
			returnResult = new ReturnResult(ReturnResultStatus.ERROR, "Restaurant  Name Mismatched witn Id "+order.getRestId());
			return returnResult;
		}
		
		if(order.getBillNo() == 0) {
			returnResult = new ReturnResult(ReturnResultStatus.ERROR, "Invalid Bill No");
			return returnResult;
		}
		
		return returnResult;
	}
	
	public List<Order> getUnpaidOrdersForRestuarantId(final int restId) {
		List<Order> unpaidOrders = new ArrayList<Order>();
		for(final Order order: service.getAllOrders()) {
			if(order.getRestId() == restId && 
			(CommonUtil.isValid(order.getPaidStatus()) && 
			order.getPaidStatus().equals(RMSConstants.NO.getValue()))) {
				unpaidOrders.add(order);
			}
		}
		return unpaidOrders;
	}
}
