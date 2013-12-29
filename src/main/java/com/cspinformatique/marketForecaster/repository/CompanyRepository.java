package com.cspinformatique.marketForecaster.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.cspinformatique.marketForecaster.entity.Company;
import com.cspinformatique.marketForecaster.entity.User;

public interface CompanyRepository extends JpaRepository<Company, Long> {
	@Query("SELECT company FROM Company company WHERE id = (SELECT company FROM User user WHERE user = :user) ")
	public Company findByUser(@Param("user") User user);
}
