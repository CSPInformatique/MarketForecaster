package com.cspinformatique.marketForecaster.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cspinformatique.marketForecaster.entity.Forecast;
import com.cspinformatique.marketForecaster.service.CompanyService;
import com.cspinformatique.marketForecaster.service.ForecastService;
import com.cspinformatique.marketForecaster.service.UserService;

@Controller
@RequestMapping("/forecast")
public class ForecastController {
	@Autowired private CompanyService companyService;
	@Autowired private ForecastService forecastService;
	@Autowired private UserService userService;
	
	@RequestMapping(method=RequestMethod.GET, produces="application/json")
	public @ResponseBody Forecast getForecast(Principal principal){
		return forecastService.findByCompany(companyService.getUserCompany(userService.findOne(principal.getName())));
	}
	
	@RequestMapping(method=RequestMethod.GET, produces="text/html")
	public String getForecastPage(){
		return "forecast";
	}
	
	@RequestMapping(method=RequestMethod.PUT, value="/{forecastId}", produces="application/json")
	public @ResponseBody Forecast saveForecast(@PathVariable int forecastId, @RequestBody Forecast forecast){
		return this.forecastService.save(forecast);
	}
}