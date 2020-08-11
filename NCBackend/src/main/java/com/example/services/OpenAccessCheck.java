package com.example.services;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import model.Magazine;

import com.example.services.MagazineService;

@Service
public class OpenAccessCheck implements JavaDelegate {
	@Autowired
	private MagazineService magazineService;

	@Override
	public void execute(DelegateExecution execution) throws Exception {
		
		Long magazineId = Long.parseLong(execution.getVariable("magazineId").toString());
		
		Magazine magazine = magazineService.getMagazine(magazineId);
		
		if(magazine!=null) {
			if(magazine.getIsopenaccess()==true)
				execution.setVariable("OpenAccess", true);
			else
				execution.setVariable("OpenAccess", false);
		}
		
		
	}

}
