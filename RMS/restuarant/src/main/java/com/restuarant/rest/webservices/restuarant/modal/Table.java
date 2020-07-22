package com.restuarant.rest.webservices.restuarant.modal;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;

import com.restuarant.rest.webservices.restuarant.parser.CSVCell;

@Entity
@javax.persistence.Table(name="RESTUARANT_TABLE")
@IdClass(Table.class)
public class Table implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 870581741183009200L;

	@Id
	@Column(name = "RESTURANT_ID", unique = true)
	@CSVCell(label = "Restaurant ID")
	private int restId;
	
	@Column(name = "RESTURANT_NAME")
	@CSVCell(label = "Restaurant Name")
	private String restName;
	
	@Column(name = "CAPACITY")
	@CSVCell(label = "Capacity")
	private int capacity;
	
	@Column(name = "AVAILABLE_TABLE")
	@CSVCell(label = "Available Table")
	private int availableTable;
	
	@Column(name = "AVAILABILITY")
	@CSVCell(label = "Availability")
	private String availabilty;

	public Table() {}

	public Table(int restId, String restName, int capacity, int availableTable, String  availabilty) {
		super();
		this.restId = restId;
		this.restName = restName;
		this.capacity = capacity;
		this.availableTable = availableTable;
		this.availabilty = availabilty;
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
	 * @return the capacity
	 */
	public int getCapacity() {
		return capacity;
	}

	/**
	 * @param capacity the capacity to set
	 */
	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}

	/**
	 * @return the availableTable
	 */
	public int getAvailableTable() {
		return availableTable;
	}

	/**
	 * @param availableTable the availableTable to set
	 */
	public void setAvailableTable(int availableTable) {
		this.availableTable = availableTable;
	}

	/**
	 * @return the availabilty
	 */
	public String getAvailabilty() {
		return availabilty;
	}

	/**
	 * @param availabilty the availabilty to set
	 */
	public void setAvailabilty(String availabilty) {
		this.availabilty = availabilty;
	}
	
}
