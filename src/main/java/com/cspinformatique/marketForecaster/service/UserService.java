package com.cspinformatique.marketForecaster.service;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.cspinformatique.marketForecaster.entity.User;

public interface UserService extends UserDetailsService{
	public User findOne(String username);
}
