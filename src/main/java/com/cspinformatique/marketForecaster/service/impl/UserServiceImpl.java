package com.cspinformatique.marketForecaster.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.cspinformatique.marketForecaster.entity.User;
import com.cspinformatique.marketForecaster.repository.UserReposiroty;
import com.cspinformatique.marketForecaster.service.UserService;

@Service
public class UserServiceImpl implements UserService {
	@Autowired private UserReposiroty userRepository;
	
	@Override
	public User findOne(String username) {
		return this.userRepository.findByUsername(username);
	}
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		try{
			return userRepository.findByUsername(username);
		}catch(EmptyResultDataAccessException emptyResultDataAccessEx){
			throw new UsernameNotFoundException("Username " + username + " could not be found.", emptyResultDataAccessEx);
		}
	}

}
