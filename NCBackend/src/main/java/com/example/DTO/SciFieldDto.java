package com.example.DTO;

import model.Scientificfield;

public class SciFieldDto {

	private Long id;

	private String name;

	public SciFieldDto() {
	}

	public SciFieldDto(Scientificfield sciField) {
		this.setName(sciField.getName());
	}


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}
