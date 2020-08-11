package com.example.email;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import model.Appuser;

@Service
public class NotifyAuthorCorrection implements JavaDelegate {
	
	@Autowired
	EmailService emailService;

	@Override
	public void execute(DelegateExecution execution) throws Exception {
		// TODO Auto-generated method stub
				
		String body = "Your have to correct your text.";

		emailService.getMail().setTo("examplemailalisa@gmail.com");
		emailService.getMail().setSubject("Correct text");
		emailService.getMail().setText(body);
		emailService.sendNotificaitionSync();
		
	}

}
