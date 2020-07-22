package com.restuarant.rest.webservices.restuarant.modal;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.IdClass;

import com.restuarant.rest.webservices.restuarant.parser.CSVCell;

@Entity
@javax.persistence.Table(name="RESTUARANT")
@IdClass(Restuarant.class)
public class Restuarant  implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	@Id
	@Column(name = "RESTURANT_ID", unique = true)
	@CSVCell(label = "Restaurant ID")
	private int restId;
	@Column(name = "RESTURANT_NAME")
	@CSVCell(label = "Restaurant Name")
	private String restName;
	
	public Restuarant() {}
	
	public Restuarant(int restId, String restName) {
		super();
		this.restId = restId;
		this.restName = restName;
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
}
