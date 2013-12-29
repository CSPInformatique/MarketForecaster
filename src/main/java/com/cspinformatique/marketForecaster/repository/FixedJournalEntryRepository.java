package com.cspinformatique.marketForecaster.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cspinformatique.marketForecaster.entity.FixedJournalEntry;

public interface FixedJournalEntryRepository extends JpaRepository<FixedJournalEntry, Long> {

}
