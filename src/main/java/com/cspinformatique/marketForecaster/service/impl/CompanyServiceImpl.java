package com.cspinformatique.marketForecaster.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cspinformatique.marketForecaster.entity.Company;
import com.cspinformatique.marketForecaster.entity.User;
import com.cspinformatique.marketForecaster.repository.CompanyRepository;
import com.cspinformatique.marketForecaster.service.CompanyService;
import com.cspinformatique.marketForecaster.service.ForecastService;

@Service
public class CompanyServiceImpl implements CompanyService {
	@Autowired private CompanyRepository companyRepository; 
	
	@Autowired private ForecastService forecastService;
	
	@Override
	public Company getUserCompany(User user){
		return this.companyRepository.findByUser(user);
	}
	
	@Override
	public List<Company> getCompanies() {
		return this.companyRepository.findAll();
	}

	@Override
	public Company saveCompany(Company company) {
		return this.companyRepository.save(company);
	}
}
