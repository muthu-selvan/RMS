package com.restuarant.rest.webservices.restuarant.modal;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;

@Entity
@javax.persistence.Table(name="RESTUARANT_ORDER")
@IdClass(Order.class)
public class Order  implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "BILL_NO", unique = true)
	private int billNo;
	
	@Column(name = "RESTURANT_ID")
	private int restId;
	
	@Column(name = "RESTURANT_NAME")
	private String restName;
	
	@Column(name = "TOTAL_PRODUCTS")
	private int totalProducts;
	
	@Column(name = "TOTAL_AMOUNT")
	private int totalAmount;
	
	@Column(name = "PAID_STATUS")
	private String paidStatus;

	public Order() {}
	
	public Order(int restId, String restName, int billNo, int totalProducts, int totalAmount, String paidStatus) {
		super();
		this.restId = restId;
		this.restName = restName;
		this.billNo = billNo;
		this.totalProducts = totalProducts;
		this.totalAmount = totalAmount;
		this.paidStatus = paidStatus;
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
	 * @return the billNo
	 */
	public int getBillNo() {
		return billNo;
	}

	/**
	 * @param billNo the billNo to set
	 */
	public void setBillNo(int billNo) {
		this.billNo = billNo;
	}

	/**
	 * @return the totalProducts
	 */
	public int getTotalProducts() {
		return totalProducts;
	}

	/**
	 * @param totalProducts the totalProducts to set
	 */
	public void setTotalProducts(int totalProducts) {
		this.totalProducts = totalProducts;
	}

	/**
	 * @return the totalAmount
	 */
	public int getTotalAmount() {
		return totalAmount;
	}

	/**
	 * @param totalAmount the totalAmount to set
	 */
	public void setTotalAmount(int totalAmount) {
		this.totalAmount = totalAmount;
	}
	
	/**
	 * @return the paidStatus
	 */
	public String getPaidStatus() {
		return paidStatus;
	}

	/**
	 * @param paidStatus the paidStatus to set
	 */
	public void setPaidStatus(String paidStatus) {
		this.paidStatus = paidStatus;
	}

}
