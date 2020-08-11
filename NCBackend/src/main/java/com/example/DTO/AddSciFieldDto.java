package com.example.DTO;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import model.Scientificfield;
import com.example.services.SciFieldService;

@Component
public class AddSciFieldDto implements Converter<List<FieldDto>, List<Scientificfield>> {

	@Autowired
	SciFieldService scientificAreaService;
	
	@Override
	public List<Scientificfield> convert(List<FieldDto> source) {
		
		if(source == null) return null;
		
		List<Scientificfield> sciFields = new ArrayList<Scientificfield>();
		
		for(FieldDto field : source){
			if(field.getFieldId().equals("sciFields")){
				sciFields.add(scientificAreaService.getScientificArea(Long.parseLong(field.getFieldValue())));
			}
		}
		return sciFields;
	}
}
