package com.cspinformatique.marketForecaster.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class ForecastedJournalEntry {
	private int id;
	private Asset asset;
	private double amount;
	private String label;
	private Date timestamp;

	public ForecastedJournalEntry(){
		
	}
	
	public ForecastedJournalEntry(
		int id,
		Asset asset, 
		double amount, 
		String label, 
		Date timestamp
	) {
		this.id = id;
		this.amount = amount;
		this.label = label;
		this.timestamp = timestamp;
	}

	@Id
	@GeneratedValue
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@OneToOne
	public Asset getAsset() {
		return asset;
	}

	public void setAsset(Asset asset) {
		this.asset = asset;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public Date getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}
}
