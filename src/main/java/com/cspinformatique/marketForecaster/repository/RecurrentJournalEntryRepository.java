package com.cspinformatique.marketForecaster.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cspinformatique.marketForecaster.entity.RecurrentJournalEntry;

public interface RecurrentJournalEntryRepository extends	JpaRepository<
																RecurrentJournalEntry, 
																Long
															> {

}
