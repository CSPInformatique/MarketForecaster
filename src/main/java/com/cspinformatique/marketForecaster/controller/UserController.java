package com.cspinformatique.marketForecaster.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cspinformatique.marketForecaster.entity.User;
import com.cspinformatique.marketForecaster.service.UserService;

@Controller
@RequestMapping("/user")
public class UserController {
	@Autowired private UserService userService;
	
	@RequestMapping(method=RequestMethod.GET)
	public @ResponseBody User getUser(Principal principal){
		return this.userService.findOne(principal.getName()); 
	}
}