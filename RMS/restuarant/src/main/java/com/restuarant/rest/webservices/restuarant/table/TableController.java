package com.restuarant.rest.webservices.restuarant.table;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.restuarant.rest.webservices.restuarant.modal.Table;
import com.restuarant.rest.webservices.restuarant.returnresult.ReturnResult;

@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*")
@RestController
public class TableController {
	
	@Autowired TableControllerHelper helper;
	
	@GetMapping(path = "/RMS/getAllTables")
	public List<Table> getAllTables() {
		return helper.getAllTables();
	}
	
	@DeleteMapping(path = "/RMS/deleteTable/{restId}")
	public ReturnResult deleteTable(@PathVariable("restId") int restId) {
		return helper.deleteTable(restId);
	}
	
	@PostMapping(path = "/RMS/addTable/{restId}", consumes = "application/json")
	public ReturnResult addTable(@PathVariable("restId") String restId, @RequestBody Table table) {
		return helper.addTable(table);
	}
}
