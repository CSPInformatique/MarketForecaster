package com.cspinformatique.marketForecaster.service;

import java.util.Date;
import java.util.List;

import com.cspinformatique.marketForecaster.entity.Company;
import com.cspinformatique.marketForecaster.entity.Forecast;
import com.cspinformatique.marketForecaster.entity.TreasuryPlan;

public interface ForecastService {	
	public List<TreasuryPlan> calculateTreasuryPlans(
		Forecast forecast, 
		Date startDate, 
		Date endDate
	);
	
	public Forecast findByCompany(Company company);
	
	public Forecast save(Forecast forecast);
}
