package com.example.services;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import model.Appuser;
import model.Membership;
import model.Magazine;

@Service
public class AddMembership implements JavaDelegate {
	
	@Autowired
	MembershipService membershipService;
	
	@Autowired
	AppuserService appuserService;
	
	@Autowired
	MagazineService magazineService;

	@Override
	public void execute(DelegateExecution execution) throws Exception {
		// TODO Auto-generated method stub
		Long magazineId = Long.parseLong(execution.getVariable("magazineId").toString());
		Long currentUserId = Long.parseLong(execution.getVariable("currentUserId").toString());
		
		Appuser user = appuserService.getOne(currentUserId);
		Magazine magazine = magazineService.getMagazine(magazineId);
		
		Membership m = new Membership();
		m.setAppuser(user);
		m.setMagazine(magazine);
		membershipService.addMembership(m);
	}
}
