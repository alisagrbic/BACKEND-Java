package com.example.DTO;

import java.util.ArrayList;
import java.util.List;

import model.Appuser;
import model.Magazine;
import model.Scientificfield;

public class AppuserDto {
	
	private long id;
		
	private String city;
	
	private String country;

	private String email;
	
	private String username;
	
	private String name;
	
	private String surname;
	
	private String password;
		
	private String role;
	
	private long noOfSciFields;
	
	private boolean isReviewer;
	
	private List<SciFieldDto> sci_fields;
	

	public AppuserDto() {
	}

	public AppuserDto(Appuser user) {
		this.setSurname(user.getSurname());
		this.setName(user.getName());
		this.setCity(user.getCity());
		this.setCountry(user.getCountry());
		this.setEmail(user.getEmail());
		this.setPassword(user.getPassword());
		this.setUsername(user.getUsername());
		this.setRole(user.getRole());
		this.setNoOfSciFields(user.getScientificfields().size());
		
		this.sci_fields = new ArrayList<SciFieldDto>();
		for(Scientificfield p : user.getScientificfields()){
			SciFieldDto sciDTO = new SciFieldDto(p);
			this.sci_fields.add(sciDTO);
		}
		
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public long getNoOfSciFields() {
		return noOfSciFields;
	}

	public void setNoOfSciFields(long noOfSciFields) {
		this.noOfSciFields = noOfSciFields;
	}

	public boolean isReviewer() {
		return isReviewer;
	}

	public void setReviewer(boolean isReviewer) {
		this.isReviewer = isReviewer;
	}

}
