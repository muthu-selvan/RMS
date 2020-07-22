package com.restuarant.rest.webservices.restuarant.product;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.restuarant.rest.webservices.restuarant.modal.Product;
import com.restuarant.rest.webservices.restuarant.returnresult.ReturnResult;

@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*")
@RestController
public class ProductController {
	
	@Autowired ProductControllerHelper helper;
	
	@GetMapping(path = "/RMS/getAllProducts")
	public List<Product> getAllProducts() {
		return helper.getAllProducts();
	}
	
	@DeleteMapping(path = "/RMS/deleteProduct/{restId}")
	public ReturnResult deleteProduct(@PathVariable("restId") int restId) {
		return helper.deleteProductById(restId);
	}
	
	@PostMapping(path = "/RMS/addProduct/{restId}", consumes = "application/json")
	public ReturnResult addProduct(@PathVariable("restId") String restId, @RequestBody Product product) {
		return helper.addProduct(product);
	}
}
