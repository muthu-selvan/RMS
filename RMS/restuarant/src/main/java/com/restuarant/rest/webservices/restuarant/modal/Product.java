package com.restuarant.rest.webservices.restuarant.modal;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;

@Entity
@javax.persistence.Table(name="RESTUARANT_PRODUCT")
@IdClass(Product.class)
public class Product  implements Serializable  {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "RESTURANT_ID", unique = true)
	private int restId;
	
	@Column(name = "RESTURANT_NAME")
	private String restName;
	
	@Column(name = "PRICE")
	private int price;
	
	@Column(name = "DESCRIPTION")
	private String description;
	
	@Column(name = "PRODUCT_NAME")
	private String productName;
	
	public Product() {}
	
	public Product(int restId, String restName, String productName,int price, String description) {
		super();
		this.restId = restId;
		this.restName = restName;
		this.setProductName(productName);
		this.price = price;
		this.description = description;
	}

	
	/**
	 * @return the restId
	 */
	public int getRestId() {
		return restId;
	}

	/**
	 * @param restId the restId to set
	 */
	public void setRestId(int restId) {
		this.restId = restId;
	}

	/**
	 * @return the restName
	 */
	public String getRestName() {
		return restName;
	}

	/**
	 * @param restName the restName to set
	 */
	public void setRestName(String restName) {
		this.restName = restName;
	}

	/**
	 * @return the price
	 */
	public int getPrice() {
		return price;
	}

	/**
	 * @param price the price to set
	 */
	public void setPrice(int price) {
		this.price = price;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}


	/**
	 * @return the productName
	 */
	public String getProductName() {
		return productName;
	}


	/**
	 * @param productName the productName to set
	 */
	public void setProductName(String productName) {
		this.productName = productName;
	}
	
	

}
