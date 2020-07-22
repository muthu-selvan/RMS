package com.restuarant.rest.webservices.restuarant.product.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.restuarant.rest.webservices.restuarant.constants.ReturnResultStatus;
import com.restuarant.rest.webservices.restuarant.modal.Product;
import com.restuarant.rest.webservices.restuarant.returnresult.ReturnResult;
import com.restuarant.rest.webservices.restuarant.services.ProductService;

@Component
public class ProductServiceImpl implements ProductService {

	@Autowired ProductJPARepository repository;
	
	@Override
	public ReturnResult addProduct(Product product) {
		ReturnResult result = new ReturnResult(ReturnResultStatus.SUCCESS);
		try {
			repository.save(product);
			result = new ReturnResult(ReturnResultStatus.SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			result = new ReturnResult(ReturnResultStatus.ERROR, "Internal Server Error");
		}
		return result;
	}

	@Override
	public List<Product> getAllProducts() {
		// TODO Auto-generated method stub
		return repository.findAll();
	}

	@Override
	public ReturnResult deleteProduct(int restId) {
		ReturnResult result = new ReturnResult(ReturnResultStatus.SUCCESS);
		try {
			Product product = getProductById(restId);
			if(product == null) {
				result = new ReturnResult(ReturnResultStatus.ERROR, "Product Not Found for Id "+restId);
				return result;
			}
			repository.delete(product);
			result = new ReturnResult(ReturnResultStatus.SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			result = new ReturnResult(ReturnResultStatus.ERROR, "Internal Server Error");
		}
		return result;
	}

	@Override
	public ReturnResult updateProduct(Product product) {
		ReturnResult result = new ReturnResult(ReturnResultStatus.SUCCESS);
		try {
			result = deleteProduct(product.getRestId());
			if(!result.getStatus().equals(ReturnResultStatus.SUCCESS)) {
				return result;
			}
			result = addProduct(product);
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
	public Product getProductById(int restId) {
		final Optional<Product> productOptional = repository.findByRestId(restId);
		if(productOptional != null && !productOptional.isEmpty()) {
			return productOptional.get();
		}
		return null;
	}

}
