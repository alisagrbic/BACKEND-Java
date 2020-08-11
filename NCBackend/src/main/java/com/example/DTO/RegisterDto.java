package com.example.DTO;

import java.util.List;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import model.Appuser;

@Component
public class RegisterDto implements Converter<List<FieldDto>, AppuserDto> {

	@Override
	public AppuserDto convert(List<FieldDto> source) {
		
		if(source == null)
			return null;
		
		AppuserDto user = new AppuserDto();
		
		for(FieldDto field : source){
			if(field.getFieldId().equals("Name")){
				user.setName(field.getFieldValue());
			}
			if(field.getFieldId().equals("Surname")){
				user.setSurname(field.getFieldValue());
			}
			if(field.getFieldId().equals("Email")){
				user.setEmail(field.getFieldValue());
			}
			if(field.getFieldId().equals("Password")){
				user.setPassword(field.getFieldValue());
			}
			if(field.getFieldId().equals("City")){
				user.setCity(field.getFieldValue());
			}
			if(field.getFieldId().equals("Country")){
				user.setCountry(field.getFieldValue());
			}
			if(field.getFieldId().equals("Username")){
				user.setUsername(field.getFieldValue());
			}			
		}		
		return user;
	}
}
