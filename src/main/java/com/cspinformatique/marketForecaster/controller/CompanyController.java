package com.cspinformatique.marketForecaster.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cspinformatique.marketForecaster.entity.Company;
import com.cspinformatique.marketForecaster.entity.Forecast;
import com.cspinformatique.marketForecaster.service.CompanyService;
import com.cspinformatique.marketForecaster.service.ForecastService;

@Controller
@RequestMapping("/company")
public class CompanyController {
	@Autowired private CompanyService companyService;
	@Autowired private ForecastService forecastService;
	
	public String getCompaniesPage(){
		return "company/list";
	}
	
	@RequestMapping("/{companyId}/forecast")
	public @ResponseBody Forecast getCompanyForecast(Company company){
		return this.forecastService.findByCompany(company);
	}
	
	public @ResponseBody Company saveCompany(@RequestBody Company company){
		return company;
	}
	
	public @ResponseBody Forecast saveCompanyForecast(@RequestBody Forecast forecast){
		return this.forecastService.save(forecast);
	}
}
