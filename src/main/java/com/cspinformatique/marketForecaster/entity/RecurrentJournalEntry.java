package com.cspinformatique.marketForecaster.entity;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToOne;

@Entity
public class RecurrentJournalEntry extends JournalEntry {
	private Recurrence recurrence;
	private Date startDate;
	
	public RecurrentJournalEntry(){
		super();
	}

	public RecurrentJournalEntry(
		long id,
		Asset asset,
		String label, 
		double amount,
		Recurrence recurrence,
		Date startDate
	) {
		super(id, asset, label, amount);
		
		this.recurrence = recurrence;
		this.startDate = startDate;
	}

	@OneToOne(cascade=CascadeType.ALL)
	public Recurrence getRecurrence() {
		return recurrence;
	}

	public void setRecurrence(Recurrence recurrence) {
		this.recurrence = recurrence;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
}
