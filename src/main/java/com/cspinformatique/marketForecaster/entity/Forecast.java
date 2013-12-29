package com.cspinformatique.marketForecaster.entity;

import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Forecast {
	private long id;
	private Company company;
	private Set<FixedJournalEntry> fixedJournalEntries;
	private Set<RecurrentJournalEntry> recurrentJournalEntries;
	private Set<Scenario> scenarios;
	
	private Date startDate;
	private Date endDate;
	
	public Forecast(){
		
	}

	public Forecast(
		long id,
		Company company,
		Set<FixedJournalEntry> fixedJournalEntries,
		Set<RecurrentJournalEntry> recurrentJournalEntries,
		Set<Scenario> scenarios,
		Date startDate,
		Date endDate
	) {
		this.id = id;
		this.company = company;
		this.fixedJournalEntries = fixedJournalEntries;
		this.recurrentJournalEntries = recurrentJournalEntries;
		this.startDate = startDate;
		this.endDate = endDate;
	}

	@Id
	@GeneratedValue
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	@OneToOne(fetch=FetchType.EAGER)
	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	@JoinTable
	@OneToMany(fetch=FetchType.EAGER, cascade=CascadeType.ALL)
	public Set<FixedJournalEntry> getFixedJournalEntries() {
		return fixedJournalEntries;
	}

	public void setFixedJournalEntries(Set<FixedJournalEntry> fixedJournalEntries) {
		this.fixedJournalEntries = fixedJournalEntries;
	}

	@JoinTable
	@OneToMany(fetch=FetchType.EAGER, cascade=CascadeType.ALL)
	public Set<RecurrentJournalEntry> getRecurrentJournalEntries() {
		return recurrentJournalEntries;
	}

	public void setRecurrentJournalEntries(
			Set<RecurrentJournalEntry> recurrentJournalEntries) {
		this.recurrentJournalEntries = recurrentJournalEntries;
	}

	@JoinTable
	@OneToMany(fetch=FetchType.EAGER, cascade=CascadeType.ALL)
	public Set<Scenario> getScenarios() {
		return scenarios;
	}

	public void setScenarios(Set<Scenario> scenarios) {
		this.scenarios = scenarios;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
}
