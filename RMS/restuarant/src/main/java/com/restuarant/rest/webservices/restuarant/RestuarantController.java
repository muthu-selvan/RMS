package com.restuarant.rest.webservices.restuarant;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.restuarant.rest.webservices.restuarant.modal.Restuarant;
import com.restuarant.rest.webservices.restuarant.returnresult.ReturnResult;

@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*")
@RestController
public class RestuarantController {
	
	@Autowired
	RestuarantControllerHelper helper;
	
	@GetMapping(path = "/RMS/getAllRestuarants")
	public List<Restuarant> getAllRestuarants() {
		return helper.getAllRestuarants();
	}
	
	@DeleteMapping(path = "/RMS/deleteRestuarant/{restId}")
	public ReturnResult deleteRestuarant(@PathVariable("restId") int restId) {
		return helper.deleteRestuarantById(restId);
	}
	
	@PostMapping(path = "/RMS/addRestuarant/{restId}", consumes = "application/json")
	public ReturnResult addRestuarant(@PathVariable("restId") String restId, @RequestBody Restuarant restuarant) {
		return helper.addRestuarant(restuarant);
	}
	
	@GetMapping(path = "/RMS/getAllRestuarantsIdMap")
	public Map<Integer, String> getRestuarantIdNameMap() {
		final Map<Integer, String> restuarantIdNameMap = new HashMap<Integer, String>();
		final List<Restuarant> restuarants = helper.getAllRestuarants();
		
		for(final Restuarant restuarant: restuarants) {
			restuarantIdNameMap.put(restuarant.getRestId(), restuarant.getRestName());
		}
		return restuarantIdNameMap;
	}
	
}
