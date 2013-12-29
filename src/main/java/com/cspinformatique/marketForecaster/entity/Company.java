package com.cspinformatique.marketForecaster.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Company implements Serializable{
	private static final long serialVersionUID = -7551936680233491436L;
	
	private long id;
	private String name;
	
	public Company(){
		
	}
	
	public Company(long id, String name){
		this.id = id;
		this.name = name;
	}

	@Id
	@GeneratedValue
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
