package com.example.services;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import org.camunda.bpm.engine.IdentityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import com.example.DTO.AppuserDto;
import com.example.repository.AppUserRepository;
import com.example.security.JWTUtils;
import com.example.security.MyUserDetailsService;
import com.example.security.TokenDto;

import model.Appuser;

@Service
public class AppuserService {

	@Autowired
	AppUserRepository userRepository;
	
	@Autowired
	IdentityService identityService;

	@Autowired
	JWTUtils tokenUtils;
	
	public List<Appuser> getAll(){
		return userRepository.findAll();
	}
	
	public void addUser(Appuser u) {
		userRepository.save(u);
	}
	
	public void updateUser(Appuser u) {
		userRepository.save(u);
	}
	
	public Appuser getOne(Long id) {
		return userRepository.getOne(id);
	}
	
	public Appuser getbyEmail(String email) {
		return userRepository.findByEmail(email);
	}
	
	public Appuser getbyUsername(String username) {
		return userRepository.findByUsername(username);
	}
	
	public void deleteUser(Long id) {
		userRepository.deleteById(id);
	}
	
	public void deleteAllUser() {
		userRepository.deleteAll();
	}
	
	public Boolean existsUser(long id) {
		return userRepository.existsById(id);
	}

	public TokenDto generateToken(String username) {
		
		Appuser user = getbyUsername(username);
    	MyUserDetailsService customUserdetails = new MyUserDetailsService(user);
        String userToken = tokenUtils.generateToken(customUserdetails);            

        return new TokenDto(userToken);
	}
	
    public Appuser getCurrentUser() {
    	String currentUsername = "";
    	  Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    	  if (!(auth instanceof AnonymousAuthenticationToken)) {
    		  currentUsername = auth.getName();
      		  System.out.println("username ulogovan: " + currentUsername);
    	  }
    	  else
      		  System.out.println(auth.getName());

          try {             
              Appuser user = getbyUsername(auth.getName());
              return user;
          } catch (Exception e) {
              return null;
          }
    }

	public Appuser checkUser(Appuser checkLoggedIn) {
		Appuser found = userRepository.findByEmail(checkLoggedIn.getEmail());
		if(found!=null){
			if(checkLoggedIn.getPassword().equals(found.getPassword()))
				return found;
			else return null;			
		}else{
			return null;
		}
	}
	
	
	public Appuser mapDTO(AppuserDto userDto){
		
		Appuser user = new Appuser();
		
		user.setId(userDto.getId());
		user.setCity(userDto.getCity());
		user.setCountry(userDto.getCountry());
		user.setEmail("alisa.grbic@gmail.com");
		user.setName(userDto.getName());
		user.setUsername(userDto.getUsername());
		user.setSurname(userDto.getSurname());
		user.setPassword(userDto.getPassword());
		user.setRole(userDto.getRole());
		return user;
	}
	
	public AppuserDto mapToDTO(Appuser user){
		return new AppuserDto(user);
	}
	
	public List<AppuserDto> mapAllToDTO(){
		
		List<Appuser> appusers = getAll();
		List<AppuserDto> appUserDTOs = new ArrayList<>();
		
		for(Appuser r : appusers){
			appUserDTOs.add(mapToDTO(r));
		}
		
		return appUserDTOs;
	}
}