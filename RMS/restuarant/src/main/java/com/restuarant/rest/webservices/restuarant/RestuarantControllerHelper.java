package com.restuarant.rest.webservices.restuarant;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.restuarant.rest.webservices.restuarant.common.CommonUtil;
import com.restuarant.rest.webservices.restuarant.constants.ReturnResultStatus;
import com.restuarant.rest.webservices.restuarant.modal.Order;
import com.restuarant.rest.webservices.restuarant.modal.Restuarant;
import com.restuarant.rest.webservices.restuarant.modal.Table;
import com.restuarant.rest.webservices.restuarant.order.OrderControllerHelper;
import com.restuarant.rest.webservices.restuarant.returnresult.ReturnResult;
import com.restuarant.rest.webservices.restuarant.services.RestuarantService;
import com.restuarant.rest.webservices.restuarant.table.TableControllerHelper;

@Component
public class RestuarantControllerHelper {
	
	
	@Autowired RestuarantService restuarantService;
	
	@Autowired TableControllerHelper tableHelper;
	
	@Autowired OrderControllerHelper orderHelper;
	
	
	public List<Restuarant> getAllRestuarants() {
		return restuarantService.getAllRestuarants();
	}
	
	ReturnResult deleteRestuarantById(final int id) {
		ReturnResult returnResult = new ReturnResult(ReturnResultStatus.SUCCESS);
		
//		Check All orders are closed 
		final List<Order> orders = orderHelper.getUnpaidOrdersForRestuarantId(id);
		if(orders != null && !orders.isEmpty()) {
			returnResult = new ReturnResult(ReturnResultStatus.ERROR, "Please Clear Pending Orders Under This Restuarant");
			return returnResult;
		}
		
//		Verify whether tables booked
		final Table table = tableHelper.getTableForRestuarant(id);
		if(table != null) {
			returnResult = new ReturnResult(ReturnResultStatus.ERROR, "Tables Record is Available In This Restuarant");
			return returnResult;
		}
		
		return restuarantService.deleteRestuarant(id);
	}
	
	ReturnResult addRestuarant(final Restuarant restuarant) {
		final ReturnResult returnResult = validate(restuarant);
		if(returnResult.getStatus().equals(ReturnResultStatus.ERROR)) {
			return returnResult;
		}
		
		return restuarantService.addRestaurant(restuarant);
	}
	
	private ReturnResult validate(final Restuarant restuarant) {
		ReturnResult returnResult = new ReturnResult(ReturnResultStatus.SUCCESS);
		
		if(restuarant.getRestId() == 0) {
			returnResult = new ReturnResult(ReturnResultStatus.ERROR, "Invalid Restuarant Id");
			return returnResult;
		}
		
		if(isRecordExists(restuarant.getRestId())) {
			returnResult = new ReturnResult(ReturnResultStatus.ERROR, "Restuarant Id Already Exists");
			return returnResult;
		}
		
		if(!CommonUtil.isValid(restuarant.getRestName())) {
			returnResult = new ReturnResult(ReturnResultStatus.ERROR, "Invalid Restuarant Name");
			return returnResult;
		}
		
		return returnResult;
	}
	
	private boolean isRecordExists(final int restuarantId) {
		Restuarant restuarant = restuarantService.getRestuarantById(restuarantId);
		if(restuarant != null) {
			return true;
		}
		return false;
	}

}
