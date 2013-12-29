package com.cspinformatique.marketForecaster.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cspinformatique.marketForecaster.entity.User;

public interface UserReposiroty extends JpaRepository<User, Integer> {
	public User findByUsername(String username);
}
