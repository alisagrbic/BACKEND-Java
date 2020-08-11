package com.example.email;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;


@Service
public class RegistrationEmail implements JavaDelegate {
	
	@Autowired
	private JavaMailSender mailSender;

	@Autowired
	private Environment environment;
	
	@Override
	public void execute(DelegateExecution execution) throws Exception {
		/*// TODO Auto-generated method stub

		String message = "";

		message += "You have successfully submitted your registration.";
		
		try {
			MailService.sendEmail(mailSender, environment, "Registracija", message, "examplemailalisa@gmail.com");
		}catch(Exception e) {
			System.out.println("* greska_slanje_poruke *");
		}*/
		
		
	}

}
