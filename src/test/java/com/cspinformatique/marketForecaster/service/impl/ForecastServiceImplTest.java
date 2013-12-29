package com.cspinformatique.marketForecaster.service.impl;

import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.cspinformatique.marketForecaster.entity.Forecast;
import com.cspinformatique.marketForecaster.entity.Recurrence;
import com.cspinformatique.marketForecaster.entity.RecurrentJournalEntry;
import com.cspinformatique.marketForecaster.repository.ForecastRepository;
import com.cspinformatique.marketForecaster.service.impl.ForecastServiceImpl;

@RunWith(MockitoJUnitRunner.class)
public class ForecastServiceImplTest {
	@Mock private ForecastRepository forecastRepository;
	
	@InjectMocks private ForecastServiceImpl forecastServiceImpl;
	
	@Test
	public void calculateRecurrenteJournalEntriesTest(){
		RecurrentJournalEntry journalEntry =	new RecurrentJournalEntry(
													0, 
													null, 
													"Loyer", 
													1400d, 
													new Recurrence(
														0,
														null, 
														new HashSet<Integer>(Arrays.asList(new Integer[]{1})), 
														null, 
														null, 
														null, 
														null
													),
													null
												);
		
		Forecast forecast = new Forecast();
		forecast.setRecurrentJournalEntries(new HashSet<RecurrentJournalEntry>(Arrays.asList(new RecurrentJournalEntry[]{journalEntry})));
		
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.MONTH, 12);
		
		Assert.assertEquals(
			this.forecastServiceImpl.calculateRecurrentJournalEntries(
				forecast, 
				new Date(), 
				calendar.getTime()
			).size(),
			12
		);
		
		journalEntry.setRecurrence(
			new Recurrence(
				0,
				null, 
				new HashSet<Integer>(Arrays.asList(new Integer[]{1, 14})), 
				null, 
				null, 
				null, 
				null
			)
		);
		
		Assert.assertEquals(
			this.forecastServiceImpl.calculateRecurrentJournalEntries(
				forecast, 
				new Date(), 
				calendar.getTime()
			).size(),
			24
		);
		
		journalEntry.setRecurrence(
			new Recurrence(
				0,
				null, 
				new HashSet<Integer>(Arrays.asList(new Integer[]{1, 14})), 
				null, 
				null, 
				new HashSet<Integer>(Arrays.asList(new Integer[]{1, 3, 5, 7, 9, 11})), 
				null
			)
		);
		
		Assert.assertEquals(
			this.forecastServiceImpl.calculateRecurrentJournalEntries(
				forecast, 
				new Date(), 
				calendar.getTime()
			).size(),
			12
		);
	}
}
