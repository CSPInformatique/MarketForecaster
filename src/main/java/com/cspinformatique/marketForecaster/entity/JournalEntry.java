package com.cspinformatique.marketForecaster.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToOne;

@Entity
@Inheritance(strategy=InheritanceType.TABLE_PER_CLASS)
public abstract class JournalEntry {
	private long id;
	private Asset asset;
	private String label;
	private double amount;
	
	public JournalEntry() {
		
	}

	public JournalEntry(long id, Asset asset, String label, double amount) {
		this.id = id;
		this.asset = asset;
		this.label = label;
		this.amount = amount;
	}

	@Id
	@GeneratedValue(strategy=GenerationType.TABLE)
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	@OneToOne
	public Asset getAsset() {
		return asset;
	}

	public void setAsset(Asset asset) {
		this.asset = asset;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}
}
