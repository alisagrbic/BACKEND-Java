package com.example.services;

import java.util.List;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import model.Appuser;
import model.Membership;

@Service
public class CheckMembership implements JavaDelegate {
	
	@Autowired
	MembershipService membershipService;

	@Override
	public void execute(DelegateExecution execution) throws Exception {
		// TODO Auto-generated method stub
		List<Membership> memberships = membershipService.getAll();
		Long currentUserId = Long.parseLong(execution.getVariable("currentUserId").toString());
		
		if(memberships.isEmpty())
			execution.setVariable("ActiveMembership", false);
			
		
		
		for(Membership m: memberships) {
			Appuser user = m.getAppuser();
			if(user.getId()==currentUserId) {
				execution.setVariable("ActiveMembership", true);
			}
			else {
				execution.setVariable("ActiveMembership", false);
			}
		}
		
	}

}
