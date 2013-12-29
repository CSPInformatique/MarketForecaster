package com.cspinformatique.marketForecaster.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Recurrence {
	private int id;
	
	private Set<Integer> weekDays;
	private Set<Integer> monthDays;
	private Set<Integer> yearDays;
	private Set<Integer> weeks;
	private Set<Integer> months;
	private Set<Integer> years;
	
	public Recurrence(){
		this.weekDays = new HashSet<Integer>();
		this.monthDays = new HashSet<Integer>();
		this.yearDays = new HashSet<Integer>();
		this.weeks = new HashSet<Integer>();
		this.months = new HashSet<Integer>();
		this.years = new HashSet<Integer>();
	}
	
	public Recurrence(
		int id,
		Set<Integer> weekDays, 
		Set<Integer> monthDays,
		Set<Integer> yearDays, 
		Set<Integer> weeks,
		Set<Integer> months, 
		Set<Integer> years
	) {
		this.id = id;
		this.weekDays = weekDays;
		this.monthDays = monthDays;
		this.yearDays = yearDays;
		this.weeks = weeks;
		this.months = months;
		this.years = years;
	}
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@ElementCollection(fetch=FetchType.EAGER)
	public Set<Integer> getWeekDays() {
		return weekDays;
	}
	public void setWeekDays(Set<Integer> weekDays) {
		this.weekDays = weekDays;
	}

	@ElementCollection(fetch=FetchType.EAGER)
	public Set<Integer> getMonthDays() {
		return monthDays;
	}
	public void setMonthDays(Set<Integer> monthDays) {
		this.monthDays = monthDays;
	}

	@ElementCollection(fetch=FetchType.EAGER)
	public Set<Integer> getYearDays() {
		return yearDays;
	}
	public void setYearDays(Set<Integer> yearDays) {
		this.yearDays = yearDays;
	}

	@ElementCollection(fetch=FetchType.EAGER)
	public Set<Integer> getWeeks() {
		return weeks;
	}
	public void setWeeks(Set<Integer> weeks) {
		this.weeks = weeks;
	}

	@ElementCollection(fetch=FetchType.EAGER)
	public Set<Integer> getMonths() {
		return months;
	}
	public void setMonths(Set<Integer> months) {
		this.months = months;
	}

	@ElementCollection(fetch=FetchType.EAGER)
	public Set<Integer> getYears() {
		return years;
	}
	public void setYears(Set<Integer> years) {
		this.years = years;
	}
}
