package com.cspinformatique.marketForecaster.entity;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;

public class AssetValue {
	private int id;
	private Asset asset;
	private int year;
	private double value;
	
	public AssetValue() {
		
	}

	public AssetValue(int id, Asset asset, int year, double value) {
		super();
		this.id = id;
		this.asset = asset;
		this.year = year;
		this.value = value;
	}

	@Id
	@GeneratedValue
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Asset getAsset() {
		return asset;
	}

	public void setAsset(Asset asset) {
		this.asset = asset;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public double getValue() {
		return value;
	}

	public void setValue(double value) {
		this.value = value;
	}
}
