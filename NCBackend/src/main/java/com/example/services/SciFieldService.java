package com.example.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.DTO.SciFieldDto;
import com.example.repository.ScientificFieldRepository;

import model.Scientificfield;;

@Service
public class SciFieldService {
	
	@Autowired
	ScientificFieldRepository sciRepository;
	
	public List<Scientificfield> getAll(){
		return sciRepository.findAll();
	}
	
	public void addScientificArea(Scientificfield s) {
		sciRepository.save(s);
	}
	
	public void updateScientificArea(Scientificfield s) {
		sciRepository.save(s);
	}
	
	public Scientificfield getScientificArea(long id) {
		return sciRepository.getOne(id);
	}
	
	public void deleteScientificArea(Long id) {
		sciRepository.deleteById(id);
	}
	
	public void deleteAllScientificAreas() {
		sciRepository.deleteAll();
	}
	
	public Boolean existsScientificArea(Long id) {
		return sciRepository.existsById(id);
	}
	
	public Scientificfield mapDTO(SciFieldDto scientificAreaDTO){
			
		Scientificfield scientificArea = new Scientificfield();
		scientificArea.setName(scientificAreaDTO.getName());
			
		return scientificArea;
	}
	
	public SciFieldDto mapToDTO(Scientificfield scientificArea){
		return new SciFieldDto(scientificArea);
	}
	
	public List<SciFieldDto> mapAllToDTO(){
		
		List<Scientificfield> scientificareas = getAll();
		List<SciFieldDto> scientificAreaDTOs = new ArrayList<>();
		
		for(Scientificfield r : scientificareas){
			scientificAreaDTOs.add(mapToDTO(r));
		}
		
		return scientificAreaDTOs;
	}
}