package com.cspinformatique.marketForecaster.service;

import java.util.List;

import com.cspinformatique.marketForecaster.entity.Company;
import com.cspinformatique.marketForecaster.entity.User;

public interface CompanyService {
	public Company getUserCompany(User user);
	
	public List<Company> getCompanies();
	
	public Company saveCompany(Company company);
}
