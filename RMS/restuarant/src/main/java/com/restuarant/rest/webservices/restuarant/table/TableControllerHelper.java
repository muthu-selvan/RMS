package com.restuarant.rest.webservices.restuarant.table;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.restuarant.rest.webservices.restuarant.common.CommonUtil;
import com.restuarant.rest.webservices.restuarant.constants.RMSConstants;
import com.restuarant.rest.webservices.restuarant.constants.ReturnResultStatus;
import com.restuarant.rest.webservices.restuarant.modal.Restuarant;
import com.restuarant.rest.webservices.restuarant.modal.Table;
import com.restuarant.rest.webservices.restuarant.returnresult.ReturnResult;
import com.restuarant.rest.webservices.restuarant.services.RestuarantService;
import com.restuarant.rest.webservices.restuarant.services.TableService;

@Component
public class TableControllerHelper {
	

	@Autowired TableService service;
	
	@Autowired RestuarantService restuarantService;
	
	List<Table> getAllTables() {
		return service.getAllRestuarants();
	}
	
	
	ReturnResult deleteTable(final int restId) {
		ReturnResult returnResult = new ReturnResult(ReturnResultStatus.SUCCESS);
		final Table table = getTableForRestuarant(restId);
		if(table == null) {
			returnResult = new ReturnResult(ReturnResultStatus.ERROR, "Table Not Found");
			return returnResult;
		}
		if(table.getCapacity() != table.getAvailableTable()) {
			returnResult = new ReturnResult(ReturnResultStatus.ERROR, "Tables Booked");
			return returnResult;
		}
		return 	service.deleteTable(restId);
	}
	
	ReturnResult addTable(final Table table) {
		ReturnResult returnResult = validate(table);
		if(returnResult.getStatus().equals(ReturnResultStatus.ERROR)) {
			return returnResult;
		}
		
		if(isRecordExists(table)) {
			return updateTable(table.getRestId(), table);
		}
		
		if(table.getAvailableTable() == 0) {
			table.setAvailabilty(RMSConstants.NO.getValue());
		} else {
			table.setAvailabilty(RMSConstants.YES.getValue());
		}
		
		return service.addTable(table);
	}
	
	private boolean isRecordExists(Table table) {
		return (service.getTableById(table.getRestId()) != null);
	}

	ReturnResult updateTable(final int restId,final Table table) {
		return service.updateTable(table);
	}
	
	private ReturnResult validate(final Table table) {
		ReturnResult returnResult = new ReturnResult(ReturnResultStatus.SUCCESS);
		if(table.getRestId() == 0) {
			returnResult = new ReturnResult(ReturnResultStatus.ERROR, "Invalid Restuarant Id");
			return returnResult;
		}
		
		if(!CommonUtil.isValid(table.getRestName())) {
			returnResult = new ReturnResult(ReturnResultStatus.ERROR, "Invalid Restuarant Name");
			return returnResult;
		}
		
//		Validate Restuarant Id and Name
		final Restuarant restuarant = restuarantService.getRestuarantById(table.getRestId());
		if(restuarant == null) {
			returnResult = new ReturnResult(ReturnResultStatus.ERROR, "Restaurant "+table.getRestId()+" Not Found");
			return returnResult;
		}
		
		if(!restuarant.getRestName().equals(table.getRestName())) {
			returnResult = new ReturnResult(ReturnResultStatus.ERROR, "Restaurant  Name Mismatched witn Id "+table.getRestId());
			return returnResult;
		}
		
//		validate availabilty agaist capacity
		if(table.getAvailableTable() > table.getCapacity()) {
			returnResult = new ReturnResult(ReturnResultStatus.ERROR, "Available table Should not Exceed Capacity");
			return returnResult;
		}
		
		return returnResult;
	}
	
	public Table getTableForRestuarant(final int restId) {
		
		for(Table table: service.getAllRestuarants()) {
			if(table.getRestId() == restId) {
				return table;
			}
		}
		
		return null;
	}
}
