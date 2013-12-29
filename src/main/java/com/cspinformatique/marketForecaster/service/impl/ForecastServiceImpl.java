package com.cspinformatique.marketForecaster.service.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cspinformatique.marketForecaster.entity.Company;
import com.cspinformatique.marketForecaster.entity.FixedJournalEntry;
import com.cspinformatique.marketForecaster.entity.Forecast;
import com.cspinformatique.marketForecaster.entity.ForecastedJournalEntry;
import com.cspinformatique.marketForecaster.entity.Recurrence;
import com.cspinformatique.marketForecaster.entity.RecurrentJournalEntry;
import com.cspinformatique.marketForecaster.entity.TreasuryPlan;
import com.cspinformatique.marketForecaster.repository.ForecastRepository;
import com.cspinformatique.marketForecaster.service.ForecastService;

@Service
public class ForecastServiceImpl implements ForecastService{
	@Autowired private ForecastRepository forecastRepository;
	
	public List<ForecastedJournalEntry> calculateFixedJournalEntries(
		Forecast forecast, 
		Date startDate, 
		Date endDate
	){
		List<ForecastedJournalEntry> journalEntries = new ArrayList<ForecastedJournalEntry>();
		
		for(FixedJournalEntry entry : forecast.getFixedJournalEntries()){
			journalEntries.add(
				new ForecastedJournalEntry(
					0,
					entry.getAsset(),
					entry.getAmount(),
					entry.getLabel(),
					entry.getTimestamp()
				)
			);
		}
		
		return journalEntries;
	}
	
	public List<ForecastedJournalEntry> calculateRecurrentJournalEntries(
		Forecast forecast,
		Date startDate,
		Date endDate
	){
		List<ForecastedJournalEntry> journalEntries = new ArrayList<ForecastedJournalEntry>();
		
		for(RecurrentJournalEntry recurrentJournalEntry : forecast.getRecurrentJournalEntries()){
			Recurrence recurrence = recurrentJournalEntry.getRecurrence();
			
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(startDate);
			
			do{
				if(	recurrence.getYears() == null ||
					recurrence.getYears().size() == 0 || 
					recurrence.getYears().contains(Integer.valueOf(calendar.get(Calendar.YEAR)))
				){
					if(	recurrence.getYearDays() == null || 
						recurrence.getYearDays().size() == 0 ||
						recurrence.getYearDays().contains(Integer.valueOf(calendar.get(Calendar.DAY_OF_YEAR))
						)
					){
						if(	recurrence.getMonths() == null || 
							recurrence.getMonths().size() == 0 ||
							recurrence.getMonths().contains(Integer.valueOf(calendar.get(Calendar.MONTH))
							)
						){
							if(	recurrence.getMonthDays() == null ||
								recurrence.getMonthDays().size() == 0 ||
								recurrence.getMonthDays().contains(Integer.valueOf(calendar.get(Calendar.DAY_OF_MONTH))
								)
							){
								if(	recurrence.getWeeks() == null ||
									recurrence.getWeeks().size() == 0 ||
									recurrence.getWeeks().contains(Integer.valueOf(calendar.get(Calendar.WEEK_OF_MONTH))
									)
								){
									if(	recurrence.getWeekDays() == null ||
										recurrence.getWeekDays().size() == 0 || 
										recurrence.getWeekDays().contains(Integer.valueOf(calendar.get(Calendar.DAY_OF_WEEK))
										)
									){
										journalEntries.add(
											new ForecastedJournalEntry(
												0,
												recurrentJournalEntry.getAsset(),
												recurrentJournalEntry.getAmount(), 
												recurrentJournalEntry.getLabel(), 
												calendar.getTime()
											)
										);
									}
										
								}
							}
						}
					}
				}
				calendar.add(Calendar.DAY_OF_MONTH, 1);
			}while(calendar.getTime().getTime() <= endDate.getTime());
		}
		
		return journalEntries;
	}
	
	@Override
	public List<TreasuryPlan> calculateTreasuryPlans(
		Forecast forecast, 
		Date startDate, 
		Date endDate
	){
		Calendar endCalendar = Calendar.getInstance();
		endCalendar.setTime(endDate);
		int endYear = endCalendar.get(Calendar.YEAR);
		
		List<ForecastedJournalEntry> entries = this.calculateFixedJournalEntries(forecast, startDate, endDate);
		entries.addAll(this.calculateRecurrentJournalEntries(forecast, startDate, endDate));
		
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(startDate);
		
		Calendar entryCalendar = Calendar.getInstance();

		List<TreasuryPlan> treasuryPlans = new ArrayList<TreasuryPlan>();
		
		do{
			int treasuryPlanYear = calendar.get(Calendar.YEAR);
			
			// Looping through every month.
			Map<Integer, List<ForecastedJournalEntry>> entriesByMonth = new HashMap<Integer, List<ForecastedJournalEntry>>();
			for(int month = 1; month < 13; month++){
				List<ForecastedJournalEntry> monthEntries = new ArrayList<ForecastedJournalEntry>();
				
				for(ForecastedJournalEntry entry : entries){
					entryCalendar.setTime(entry.getTimestamp());
					
					if(	entryCalendar.get(Calendar.MONTH) == month && 
						entryCalendar.get(Calendar.YEAR) == treasuryPlanYear
					){
						monthEntries.add(entry);
					}
				}
			}
			
			treasuryPlans.add(new TreasuryPlan(entriesByMonth, forecast, treasuryPlanYear));
			
			calendar.add(Calendar.YEAR, 1);
		}while(calendar.get(Calendar.YEAR) <= endYear);
		
		return treasuryPlans;
	}
	
	@Override
	public Forecast findByCompany(Company company){
		return this.forecastRepository.findByCompany(company);
	}
	
	@Override
	public Forecast save(Forecast forecast){
		return this.forecastRepository.save(forecast);
	}
}
