package com.example.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.DTO.MagazineDto;
import com.example.repository.MagazineRepository;
import com.example.repository.MembershipRepository;

import model.Magazine;
import model.Membership;

@Service
public class MembershipService {
	
	@Autowired
	MembershipRepository membershipRepository;
	
	public List<Membership> getAll(){
		return membershipRepository.findAll();
	}
	
	public void addMembership(Membership s) {
		membershipRepository.save(s);
	}
		
	public void updateMembership(Membership s) {
		membershipRepository.save(s);
	}
	
	public Membership getMembership(Long id) {
		return membershipRepository.getOne(id);
	}

	public void deleteMembership(Long id) {
		membershipRepository.deleteById(id);
	}
	
	public void deleteAllMemberships() {
		membershipRepository.deleteAll();
	}
	
	
	public Boolean existsMembership(Long id) {
		return membershipRepository.existsById(id);
	}
	
}
