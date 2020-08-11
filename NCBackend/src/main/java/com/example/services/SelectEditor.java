package com.example.services;

import java.util.HashSet;
import java.util.Set;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.email.EmailService;
import com.example.enums.Roles;
import model.Appuser;

import model.Magazine;
import model.Scientificfield;

@Service
public class SelectEditor implements JavaDelegate {
	
	@Autowired
	private MagazineService magazineService;
	
	@Autowired
	private AppuserService appuserService;
	

	@Override
	public void execute(DelegateExecution execution) throws Exception {
		// TODO Auto-generated method stub
		
		Long magazineId = Long.parseLong(execution.getVariable("magazineId").toString());		
		Magazine magazine = magazineService.getMagazine(magazineId);
		
		Appuser editor = new Appuser();
		
		for(Scientificfield sci : magazine.getScientificfields()){
			for(Appuser user : appuserService.getAll()){
				if(user.getScientificfields().contains(sci) && user.getRole().equals(Roles.EDITOR.toString()))
					editor = user;
			}
		}
		
		execution.setVariable("editor", editor);		
		
	}

}
