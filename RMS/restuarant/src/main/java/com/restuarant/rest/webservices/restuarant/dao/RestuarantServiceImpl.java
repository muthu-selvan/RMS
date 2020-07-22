package com.restuarant.rest.webservices.restuarant.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.restuarant.rest.webservices.restuarant.constants.ReturnResultStatus;
import com.restuarant.rest.webservices.restuarant.modal.Restuarant;
import com.restuarant.rest.webservices.restuarant.returnresult.ReturnResult;
import com.restuarant.rest.webservices.restuarant.services.RestuarantService;

@Component
public class RestuarantServiceImpl implements RestuarantService {

	@Autowired
	RestuarantJPARepository repository;
	
	@Override
	public ReturnResult addRestaurant(Restuarant restuarant) {
		ReturnResult result = null;
		try {
			repository.save(restuarant);
			result = new ReturnResult(ReturnResultStatus.SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			result = new ReturnResult(ReturnResultStatus.ERROR, "Internal Server Error");
		}
		return result;
	}

	@Override
	public List<Restuarant> getAllRestuarants() {
		// TODO Auto-generated method stub
		return repository.findAll();
	}

	@Override
	public ReturnResult deleteRestuarant(int restId) {
		ReturnResult result = null;
		try {
			Restuarant restuarant = getRestuarantById(restId);
			if(restuarant == null) {
				result = new ReturnResult(ReturnResultStatus.ERROR, "Restuarant Not Found for Id "+restId);
				return result;
			}
			repository.delete(restuarant);
			result = new ReturnResult(ReturnResultStatus.SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			result = new ReturnResult(ReturnResultStatus.ERROR, "Internal Server Error");
		}
		return result;
	}

	@Override
	public ReturnResult updateRestuarant(final Restuarant restuarant) {
		ReturnResult result = null;
		try {
			repository.save(restuarant);
			result = new ReturnResult(ReturnResultStatus.SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			result = new ReturnResult(ReturnResultStatus.ERROR, "Internal Server Error");
		}
		return result;
	}

	@Override
	public Restuarant getRestuarantById(int restId) {
		Optional<Restuarant> resturantOptional = repository.findByRestId(restId);
		if(resturantOptional != null && !resturantOptional.isEmpty()) {
			return resturantOptional.get();
		}
		return null;
	}

}
