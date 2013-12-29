package com.cspinformatique.marketForecaster.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cspinformatique.marketForecaster.entity.Company;
import com.cspinformatique.marketForecaster.entity.Forecast;

public interface ForecastRepository extends JpaRepository<Forecast, Long> {
	public Forecast findByCompany(Company company);
}
