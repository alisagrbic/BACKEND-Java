package com.example.DTO;

import java.util.ArrayList;

import model.Appuser;
import model.Magazine;
import model.Scientificfield;

public class MagazineDto {

	private Long id;

	private AppuserDto creator;

	private String description;

	private boolean isopenaccess;

	private ArrayList<SciFieldDto> sciFieldDto;
	
	private String title;

	public MagazineDto() { }
	
	public MagazineDto(Magazine magazine) {
		this.setId(magazine.getId());
		this.setTitle(magazine.getTitle());
		this.setDescription(magazine.getDescription());
		this.setIsopenaccess(magazine.getIsopenaccess());
		
		this.sciFieldDto = new ArrayList<SciFieldDto>();
		for(Scientificfield p : magazine.getScientificfields()){
			SciFieldDto sciFieldDto = new SciFieldDto(p);
			this.sciFieldDto.add(sciFieldDto);
		}	
	}
	
	
	
	public MagazineDto(Long id){
		this.setId(id);
	}
	
	
	
	
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public boolean isIsopenaccess() {
		return isopenaccess;
	}

	public void setIsopenaccess(boolean isopenaccess) {
		this.isopenaccess = isopenaccess;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public ArrayList<SciFieldDto> getSciFieldDto() {
		return sciFieldDto;
	}

	public void setSciFieldDto(ArrayList<SciFieldDto> sciFieldDto) {
		this.sciFieldDto = sciFieldDto;
	}

	public AppuserDto getCreator() {
		return creator;
	}

	public void setCreator(AppuserDto creator) {
		this.creator = creator;
	}
}
