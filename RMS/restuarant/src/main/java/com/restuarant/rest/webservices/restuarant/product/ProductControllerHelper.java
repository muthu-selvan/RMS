package com.restuarant.rest.webservices.restuarant.product;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.restuarant.rest.webservices.restuarant.common.CommonUtil;
import com.restuarant.rest.webservices.restuarant.constants.ReturnResultStatus;
import com.restuarant.rest.webservices.restuarant.modal.Product;
import com.restuarant.rest.webservices.restuarant.modal.Restuarant;
import com.restuarant.rest.webservices.restuarant.returnresult.ReturnResult;
import com.restuarant.rest.webservices.restuarant.services.ProductService;
import com.restuarant.rest.webservices.restuarant.services.RestuarantService;

@Component
public class ProductControllerHelper {
	
	@Autowired ProductService service;
	
	@Autowired RestuarantService restuarantService;
	
	public List<Product> getAllProducts() {
		return service.getAllProducts();
	}
	
	ReturnResult deleteProductById(final int id) {
		return service.deleteProduct(id);
	}
	
	ReturnResult addProduct(final Product product) {
		final ReturnResult returnResult = validate(product);
		if(returnResult.getStatus().equals(ReturnResultStatus.ERROR)) {
			return returnResult;
		}
		
		if(isRecordExists(product)) {
			service.updateProduct(product);
		}
		
		return service.addProduct(product);
	}
	
	private boolean isRecordExists(final Product product) {
		return (service.getProductById(product.getRestId()) != null);
	}

	private ReturnResult validate(final Product product) {
		ReturnResult returnResult = new ReturnResult(ReturnResultStatus.SUCCESS);
		if(product.getRestId() == 0) {
			returnResult = new ReturnResult(ReturnResultStatus.ERROR, "Invalid Restuarant Id");
			return returnResult;
		}
		
		if(!CommonUtil.isValid(product.getRestName())) {
			returnResult = new ReturnResult(ReturnResultStatus.ERROR, "Invalid Restuarant Name");
			return returnResult;
		}
		
//		Validate Restuarant Id and Name
		final Restuarant restuarant = restuarantService.getRestuarantById(product.getRestId());
		if(restuarant == null) {
			returnResult = new ReturnResult(ReturnResultStatus.ERROR, "Restaurant "+product.getRestId()+" Not Found");
			return returnResult;
		}
		
		if(!restuarant.getRestName().equals(product.getRestName())) {
			returnResult = new ReturnResult(ReturnResultStatus.ERROR, "Restaurant  Name Mismatched witn Id "+product.getRestId());
			return returnResult;
		}
		
		return returnResult;
	}
}
