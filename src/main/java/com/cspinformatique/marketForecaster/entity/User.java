package com.cspinformatique.marketForecaster.entity;

import java.util.Arrays;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Transient;

import org.springframework.security.core.userdetails.UserDetails;

@Entity
public class User implements UserDetails{
	private static final long serialVersionUID = 7024817897994731752L;
	
	private int id;
	private Company company;
	private String username;
	private String password;
	
	public User(){
		
	}
	
	public User(int id, Company company, String username, String password) {
		this.id = id;
		this.company = company;
		this.username = username;
		this.password = password;
	}

	@Id
	@GeneratedValue
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@OneToOne
	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	@Override
	@Transient
	public List<Role> getAuthorities() {
		return Arrays.asList(new Role[]{new Role(0, Role.type.USER.toString())});
	}

	@Override
	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@Override
	@Transient
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	@Transient
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	@Transient
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	@Transient
	public boolean isEnabled() {
		return true;
	}

}
