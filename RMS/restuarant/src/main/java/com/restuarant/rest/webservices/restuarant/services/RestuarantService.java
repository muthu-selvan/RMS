package com.restuarant.rest.webservices.restuarant.services;

import java.util.List;

import com.restuarant.rest.webservices.restuarant.modal.Restuarant;
import com.restuarant.rest.webservices.restuarant.returnresult.ReturnResult;

public interface RestuarantService {
	
	public ReturnResult addRestaurant(Restuarant restuarant);
	
	public List<Restuarant> getAllRestuarants();
	
	public ReturnResult deleteRestuarant(int restId);
	
	public ReturnResult updateRestuarant(Restuarant restuarant);
	
	public Restuarant getRestuarantById(int restId);
}
