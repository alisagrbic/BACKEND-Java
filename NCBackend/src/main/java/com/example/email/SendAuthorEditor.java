package com.example.email;

import java.util.HashMap;
import java.util.List;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.DTO.FieldDto;
import com.example.services.AppuserService;
import com.example.services.MagazineService;

import model.Appuser;
import model.Article;
import model.Magazine;

@Service
public class SendAuthorEditor implements JavaDelegate {
	
	@Autowired
	private EmailService emailService;
	
	@Autowired
	private MagazineService magazineService;
	
	@Autowired
	private AppuserService appuserService;
	

	@Override
	public void execute(DelegateExecution execution) throws Exception {
		// TODO Auto-generated method stub
		
		Long magazineId = Long.parseLong(execution.getVariable("magazineId").toString());	
		
		Magazine magazine = magazineService.getMagazine(magazineId);		
		Appuser magazineUser = magazine.getAppuser();
		
		Long authorId = Long.parseLong(execution.getVariable("authorId").toString()); 
		Appuser author = appuserService.getOne(authorId);
		
		String body = "Hello,\\r\\n" +
     			"New article.";
		
		String email = magazineUser.getEmail();
		
		emailService.getMail().setTo(magazineUser.getEmail());
		emailService.getMail().setSubject("Add article");
		emailService.getMail().setText(body);
		emailService.sendNotificaitionSync();
		
		emailService.getMail().setTo(author.getEmail());
		emailService.getMail().setSubject("Add article");
		emailService.getMail().setText(body);
		emailService.sendNotificaitionSync();
				
	}

}
