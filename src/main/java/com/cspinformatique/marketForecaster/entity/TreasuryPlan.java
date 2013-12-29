package com.cspinformatique.marketForecaster.entity;

import java.util.List;
import java.util.Map;

public class TreasuryPlan {
	private Map<Integer, List<ForecastedJournalEntry>> entriesByMonth;
	private Forecast forecast;
	private int year;
	
	public TreasuryPlan(){
		
	}

	public TreasuryPlan(
		Map<Integer, List<ForecastedJournalEntry>> entriesByMonth,
		Forecast forecast,
		int year
	){
		this.entriesByMonth = entriesByMonth;
		this.forecast = forecast;
		this.year = year;
	}

	public Map<Integer, List<ForecastedJournalEntry>> getEntriesByMonth() {
		return entriesByMonth;
	}

	public void setEntriesByMonth(
			Map<Integer, List<ForecastedJournalEntry>> entriesByMonth) {
		this.entriesByMonth = entriesByMonth;
	}

	public Forecast getForecast() {
		return forecast;
	}

	public void setForecast(Forecast forecast) {
		this.forecast = forecast;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}
}
