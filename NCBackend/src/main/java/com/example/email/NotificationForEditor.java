package com.example.email;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import model.Appuser;

@Service
public class NotificationForEditor implements JavaDelegate {
	
	@Autowired
	EmailService emailService;

	@Override
	public void execute(DelegateExecution execution) throws Exception {
		// TODO Auto-generated method stub
		
		Appuser editor = (Appuser)execution.getVariable("editor");
		
		String body = "You need to choose 2 reviewers for reviewing.";

		emailService.getMail().setTo("examplemailalisa@gmail.com");
		emailService.getMail().setSubject("Choose reviewers");
		emailService.getMail().setText(body);
		emailService.sendNotificaitionSync();
		
	}

}
