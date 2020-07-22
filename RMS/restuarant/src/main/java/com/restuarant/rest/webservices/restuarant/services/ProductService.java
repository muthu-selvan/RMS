package com.restuarant.rest.webservices.restuarant.services;

import java.util.List;

import com.restuarant.rest.webservices.restuarant.modal.Product;
import com.restuarant.rest.webservices.restuarant.returnresult.ReturnResult;

public interface ProductService {
	
	public ReturnResult addProduct(Product product);
	
	public List<Product> getAllProducts();
	
	public ReturnResult deleteProduct(int restId);
	
	public ReturnResult updateProduct(Product product);
	
	public Product getProductById(int restId);

}
