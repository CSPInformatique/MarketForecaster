package com.cspinformatique.marketForecaster.entity;

import java.util.Date;

import javax.persistence.Entity;

@Entity
public class FixedJournalEntry extends JournalEntry {
	private Date timestamp;

	public FixedJournalEntry(){
		super();
	}
	
	public FixedJournalEntry(
		long id,
		Asset asset, 
		String label, 
		double amount, 
		Date timestamp
	) {
		super(id, asset, label, amount);
		this.timestamp = timestamp;
	}
	
	public Date getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}
}
