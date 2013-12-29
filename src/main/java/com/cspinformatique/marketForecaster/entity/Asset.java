package com.cspinformatique.marketForecaster.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Asset {
	private long id;
	private double annualGrowth;
	private double initialValue;
	
	public Asset(){
		
	}
	
	public Asset(long id, double annualGrowth, double initialValue){
		this.id = id;
		this.annualGrowth = annualGrowth;
		this.initialValue = initialValue;
	}

	@Id
	@GeneratedValue
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public double getAnnualGrowth() {
		return annualGrowth;
	}

	public void setAnnualGrowth(double annualGrowth) {
		this.annualGrowth = annualGrowth;
	}

	public double getInitialValue() {
		return initialValue;
	}

	public void setInitialValue(double initialValue) {
		this.initialValue = initialValue;
	}
}
