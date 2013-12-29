package com.cspinformatique.marketForecaster.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Transient;

import org.springframework.security.core.GrantedAuthority;

@Entity
public class Role implements GrantedAuthority{
	private static final long serialVersionUID = 1916330272585475972L;
	
	public enum type{ADMIN, USER}
	
	private int id;
	private String name;
	
	public Role(){
		
	}

	public Role(int id, String name) {
		this.id = id;
		this.name = name;
	}

	@Id
	@GeneratedValue
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	@Transient
	public String getAuthority() {
		return this.getName();
	}
}
