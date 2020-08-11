package com.example.email;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TextRejected implements JavaDelegate {
	
	@Autowired
	private EmailService emailService;

	@Override
	public void execute(DelegateExecution execution) throws Exception {
		// TODO Auto-generated method stub
		String email = "examplemailalisa@gmail.com";
		
		 String body = "Hello, " +
	         			"Sorry, your article is not relevant or your time is up. Please, try again.";
		
		emailService.getMail().setTo(email);
		emailService.getMail().setSubject("Article rejected");
		emailService.getMail().setText(body);
		emailService.sendNotificaitionSync();
	}

}
