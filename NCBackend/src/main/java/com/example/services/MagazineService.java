package com.example.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.DTO.MagazineDto;
import com.example.repository.MagazineRepository;
import model.Magazine;

@Service
public class MagazineService {
	
	@Autowired
	MagazineRepository magazineRepository;
	
	public List<Magazine> getAll(){
		return magazineRepository.findAll();
	}
	
	public void addMagazine(Magazine s) {
		magazineRepository.save(s);
	}
	
	public void updateMagazine(Magazine s) {
		magazineRepository.save(s);
	}
	
	public Magazine getMagazine(Long id) {
		return magazineRepository.getOne(id);
	}

	public void deleteMagazine(Long id) {
		magazineRepository.deleteById(id);
	}
	
	public void deleteAllMagazines() {
		magazineRepository.deleteAll();
	}
	
	public Magazine getMagazineByTitle(String title) {
		return magazineRepository.findByTitle(title);
	}
	
	public Boolean existsMagazine(Long id) {
		return magazineRepository.existsById(id);
	}
	
	public Magazine mapDTO(MagazineDto magazineDto){
		
		Magazine magazine = new Magazine();
		
		magazine.setTitle(magazineDto.getTitle());
		magazine.setDescription(magazineDto.getDescription());
		magazine.setIsopenaccess(magazineDto.isIsopenaccess());
	
		return magazine;
	}
	
	public MagazineDto mapToDTO(Magazine magazine){
		return new MagazineDto(magazine);
	}
	
	public List<MagazineDto> mapAllToDTO(){
		
		List<Magazine> magazines = getAll();
		List<MagazineDto> magazineDTOs = new ArrayList<>();
		
		for(Magazine r : magazines){
			magazineDTOs.add(mapToDTO(r));
		}
		
		return magazineDTOs;
	}
}