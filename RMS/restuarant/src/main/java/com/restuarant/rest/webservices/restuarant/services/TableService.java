package com.restuarant.rest.webservices.restuarant.services;

import java.util.List;

import com.restuarant.rest.webservices.restuarant.modal.Restuarant;
import com.restuarant.rest.webservices.restuarant.modal.Table;
import com.restuarant.rest.webservices.restuarant.returnresult.ReturnResult;

public interface TableService {
	
	public ReturnResult addTable(Table table);
	
	public List<Table> getAllRestuarants();
	
	public ReturnResult deleteTable(int restId);
	
	public ReturnResult updateTable(Table table);
	
	public Table getTableById(int restId);
}
