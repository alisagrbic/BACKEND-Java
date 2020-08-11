package com.example.services;

import java.util.List;

import org.camunda.bpm.engine.IdentityService;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.example.DTO.FormSubmissionDto;
import com.example.DTO.RegisterDto;
import com.example.DTO.AddSciFieldDto;
import com.example.DTO.AppuserDto;
import com.example.enums.Roles;
import com.example.DTO.FieldDto;

import model.Appuser;
import org.camunda.bpm.engine.identity.User;



@Service
public class RegisterService implements JavaDelegate {
	
	@Autowired
	private RegisterDto registerDto;
	
	@Autowired
	private AppuserService userService;
		
	@Autowired
	IdentityService identityService;
	
	@Autowired
	private AddSciFieldDto sciFieldDto;
	
	@Override
	public void execute(DelegateExecution execution) throws Exception {
		List<FieldDto> userData = (List<FieldDto>) execution.getVariable("registration");
		List<FieldDto> sciFields = (List<FieldDto>) execution.getVariable("sciFields");
		
		AppuserDto createdUser = registerDto.convert(userData);
		createdUser.setRole(Roles.REVIEWER.toString());
		Appuser newUser = userService.mapDTO(createdUser);
		newUser.setScientificfields(sciFieldDto.convert(sciFields));
		
		BCryptPasswordEncoder bc = new BCryptPasswordEncoder(); 
        newUser.setPassword(bc.encode(createdUser.getPassword()));
		
        User camundaUser = identityService.newUser(createdUser.getUsername());
        camundaUser.setEmail(createdUser.getEmail());
        camundaUser.setFirstName(createdUser.getName());
        camundaUser.setLastName(createdUser.getSurname());
        camundaUser.setPassword(createdUser.getPassword());
        identityService.saveUser(camundaUser);

		if(createdUser != null){
			 userService.addUser(newUser);
		}		
	}
}
