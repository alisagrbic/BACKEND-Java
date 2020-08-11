package com.example.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.camunda.bpm.engine.FormService;
import org.camunda.bpm.engine.IdentityService;
import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.TaskService;
import org.camunda.bpm.engine.form.FormField;
import org.camunda.bpm.engine.form.TaskFormData;
import org.camunda.bpm.engine.impl.form.type.EnumFormType;
import org.camunda.bpm.engine.runtime.ProcessInstance;
import org.camunda.bpm.engine.task.Task;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.jvnet.hk2.config.GenerateServiceFromMethod;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.DTO.FieldDto;
import com.example.DTO.FormFieldsDto;
import com.example.DTO.LoginDto;
import com.example.DTO.MagazineDto;
import com.example.DTO.TaskDto;
import com.example.DTO.AppuserDto;
import com.example.DTO.ArticleDto;
import com.example.security.TokenDto;
import com.example.services.AppuserService;
import com.example.services.MagazineService;
import com.example.services.UploadFileService;

import model.Appuser;
import model.Article;
import model.Magazine;

@RestController
@RequestMapping("/magazine")
public class MagazineController {
	
	@Autowired
	IdentityService identityService;
	
	@Autowired
	private RuntimeService runtimeService;
	
	@Autowired
	TaskService taskService;  
	
	@Autowired
	FormService formService;
	
	@Autowired
	AppuserService userService;
	
	@Autowired
	MagazineService magazineService;
	
	@Autowired
	UploadFileService uploadService;
	
	@GetMapping(path = "/get", produces = "application/json")
    public @ResponseBody FormFieldsDto get() {
		ProcessInstance pi = runtimeService.startProcessInstanceByKey("Magazine_Process");
		Task task = taskService.createTaskQuery().processInstanceId(pi.getId()).list().get(0);
		TaskFormData tfd = formService.getTaskFormData(task.getId());
		List<FormField> properties = tfd.getFormFields();
		
        return new FormFieldsDto(task.getId(), pi.getId(), properties);
    }
	
	@GetMapping(path = "/get/tasks/{processInstanceId}", produces = "application/json")
    public @ResponseBody ResponseEntity<List<TaskFormData>> get(@PathVariable String processInstanceId) {
		
		List<Task> tasks = taskService.createTaskQuery().processInstanceId(processInstanceId).list();
		List<TaskDto> dtos = new ArrayList<TaskDto>();
		List<FormField> properties = new ArrayList<FormField>();
		for (Task task : tasks) {
			TaskDto t = new TaskDto(task.getId(), task.getName(), task.getAssignee());
			dtos.add(t);
			
			//uzmi formu
			TaskFormData tfd = formService.getTaskFormData(task.getId());
			 properties = tfd.getFormFields();
		}	
		
		//FormSubmissionDto formReg = (FormSubmissionDto) runtimeService.getVariable(processInstanceId, "registration");
				
        return new ResponseEntity(properties, HttpStatus.OK);
    }
	
	@PostMapping(path = "/post/{taskId}", produces = "application/json")
    public @ResponseBody ResponseEntity post(@RequestBody List<FieldDto> dto, @PathVariable String taskId) {
		HashMap<String, Object> map = this.mapListToDto(dto);

		Task task = taskService.createTaskQuery().taskId(taskId).singleResult();
		String processInstanceId = task.getProcessInstanceId();
		//runtimeService.setVariable(processInstanceId, "registration", dto);
		formService.submitTaskForm(taskId, map);
		
        return new ResponseEntity<>(HttpStatus.OK);
    }
	
	private HashMap<String, Object> mapListToDto(List<FieldDto> list)
	{
		HashMap<String, Object> map = new HashMap<String, Object>();
		for(FieldDto temp : list){
			map.put(temp.getFieldId(), temp.getFieldValue());
		}
		
		return map;
	}
	
	@GetMapping(path = "/get/firstTask/{processInstanceId}", produces = "application/json")
    public @ResponseBody ResponseEntity<List<TaskDto>> getTask(@PathVariable String processInstanceId) {
		
		List<Task> tasks = taskService.createTaskQuery().processInstanceId(processInstanceId).list();
		List<TaskDto> dtos = new ArrayList<TaskDto>();
		List<FormField> properties = new ArrayList<FormField>();
		for (Task task : tasks) {
			TaskDto t = new TaskDto(task.getId(), task.getName(), task.getAssignee());
			dtos.add(t);
		}	
		//FormSubmissionDto formReg = (FormSubmissionDto) runtimeService.getVariable(processInstanceId, "registration");
				
        return new ResponseEntity(dtos, HttpStatus.OK);
    }
	
	@PostMapping(path = "/tasks/complete/{taskId}", produces = "application/json")
    public @ResponseBody ResponseEntity<List<TaskDto>> complete(@PathVariable String taskId) {
		Task taskTemp = taskService.createTaskQuery().taskId(taskId).singleResult();
		taskService.complete(taskId);
		List<Task> tasks = taskService.createTaskQuery().processInstanceId(taskTemp.getProcessInstanceId()).list();
		List<TaskDto> dtos = new ArrayList<TaskDto>();
		for (Task task : tasks) {
			TaskDto t = new TaskDto(task.getId(), task.getName(), task.getAssignee());
			dtos.add(t);
		}
		
        return new ResponseEntity<List<TaskDto>>(dtos, HttpStatus.OK);
    }
	
	
	@PostMapping(path = "/login/{taskId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody ResponseEntity<?> post(@RequestBody LoginDto loginRequestDto, @PathVariable String taskId) {
		
		try{
			  Appuser user = userService.getbyUsername(loginRequestDto.getUsername());
			  if(user != null) {

				  TokenDto tokenDto = userService.generateToken(user.getUsername());

		            Task task = taskService.createTaskQuery().taskId(taskId).singleResult();
		    		String processInstanceId = task.getProcessInstanceId();
		    		runtimeService.setVariable(processInstanceId, "exists", true);
		    		runtimeService.setVariable(processInstanceId, "currentUserId", user.getId());
		    		taskService.complete(taskId);    		
					return new ResponseEntity<>(tokenDto, HttpStatus.OK);
			  }
			  else {
					return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			  }			  
			} 
			catch(AuthenticationException e) {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
    }
	
	@GetMapping(path = "/AllMagazines", produces = "application/json")
    public @ResponseBody ResponseEntity<List<MagazineDto>> getAllMagazines() {
		
		List<Magazine> allMagazines = magazineService.getAll();
		List<MagazineDto> magazineDto = new ArrayList<>();
		
		for(Magazine c : allMagazines){
			magazineDto.add(magazineService.mapToDTO(c));
		}
		
		return new ResponseEntity<>(magazineDto, HttpStatus.OK);
    }	
	
	@RequestMapping(value = "/getMagazineByTitle/{title}/{proccessId}", method = RequestMethod.GET)
	public ResponseEntity<MagazineDto> getMagazineByTitle(@PathVariable String title, @PathVariable String proccessId) {
		Magazine m = magazineService.getMagazineByTitle(title);
		
		if (m == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}

		runtimeService.setVariable(proccessId, "magazineId", m.getId());
		runtimeService.setVariable(proccessId, "EditorOfMagazine", m.getAppuser().getUsername());
		String name = runtimeService.getVariable(proccessId, "EditorOfMagazine").toString(); 
		Appuser author = userService.getbyUsername(SecurityContextHolder.getContext().getAuthentication().getName());
		runtimeService.setVariable(proccessId, "UsernameOfAuthor", author.getUsername());
		String n = author.getUsername();

		return new ResponseEntity<>(new MagazineDto(m), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/{magazineId}", method = RequestMethod.GET)
	public ResponseEntity<MagazineDto> getMagazine(@PathVariable Long magazineId) {
		Magazine c = magazineService.getMagazine(magazineId);
		
		if (c == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}

		return new ResponseEntity<>(new MagazineDto(c), HttpStatus.OK);
	}
	
	@PostMapping(path = "/addArticle", produces = "application/json")
    public @ResponseBody ResponseEntity addArticle(@RequestParam("pdf") MultipartFile file, 
													@RequestParam("fileName") String fileName,
													@RequestParam("processInstanceId") String processInstanceId,
													@RequestParam("taskId") String taskId,
													@RequestParam("article") String article) throws IOException {

		JSONObject jsonArticle = new JSONObject(article);		
		
		fileName = fileName.replace(' ', '_');
		String filePath = uploadService.store(file, fileName);
		filePath = filePath.replace("\\", "/");
		
		HashMap<String, Object> map = new HashMap<>();
		map.put("title", jsonArticle.getString("Title"));
		map.put("abstract", jsonArticle.getString("Abstract"));
		map.put("keyWords", jsonArticle.getString("Keywords"));
		map.put("pdfLocation", filePath);
			
		runtimeService.setVariable(processInstanceId, "newArticle", map);
		
		Appuser author = userService.getbyUsername(SecurityContextHolder.getContext().getAuthentication().getName());
		runtimeService.setVariable(processInstanceId, "authorId", author.getId());
		
		formService.submitTaskForm(taskId, map);
		
        return new ResponseEntity<>(HttpStatus.OK);
    }
	
	@PostMapping(path = "/correctArticle", produces = "application/json")
    public @ResponseBody ResponseEntity correctArticle(@RequestParam("pdf") MultipartFile file, 
														@RequestParam("fileName") String fileName,
														@RequestParam("taskId") String taskId) throws IOException {
		
		fileName = fileName.replace(' ', '_');
	    String filePath = uploadService.store(file, fileName);
        filePath = filePath.replace("\\", "/");

        HashMap<String, Object> map = new HashMap<>();
	
		map.put("pdfLocation", filePath);
		
		taskService.complete(taskId);
        return new ResponseEntity<>(HttpStatus.OK);
    }


	@GetMapping(path = "/getTasksForUser", produces = "application/json")
    public @ResponseBody ResponseEntity<List<TaskDto>> getTaskFormForUser() {
		
		String activeUser = SecurityContextHolder.getContext().getAuthentication().getName();
		List<Task> tasks = taskService.createTaskQuery().taskAssignee(activeUser).active().list();
		
		List<TaskDto> dtos = new ArrayList<TaskDto>();
		for (Task task : tasks) {
			TaskDto t = new TaskDto(task.getId(), task.getName(), task.getAssignee());
			dtos.add(t);
		}
        return new ResponseEntity<List<TaskDto>>(dtos,  HttpStatus.OK);
    }
	
	@GetMapping(path = "/getNewArticle/{taskId}", produces = "application/json")
    public @ResponseBody ResponseEntity<HashMap<String, Object>> getNewArticle(@PathVariable String taskId) {
		
		Task task = taskService.createTaskQuery().taskId(taskId).singleResult();
		String processInstanceId = task.getProcessInstanceId();
		HashMap<String, Object> map = new HashMap<>();
		map = (HashMap<String, Object>)runtimeService.getVariable(processInstanceId, "newArticle");

		
        return new ResponseEntity<HashMap<String, Object>>(map, HttpStatus.OK);
    }
	
	@GetMapping(path = "/get/{taskId}", produces = "application/json")
    public @ResponseBody FormFieldsDto getTaskForm(@PathVariable String taskId) {

		Task task = taskService.createTaskQuery().taskId(taskId).singleResult();
		TaskFormData tfd = formService.getTaskFormData(task.getId());
		List<FormField> properties = tfd.getFormFields();
		
        return new FormFieldsDto(task.getId(),task.getProcessInstanceId(), properties);
    }
	
	@PostMapping(path = "/setRelevant/{relevant}", produces = "application/json")
    public @ResponseBody ResponseEntity setRelevant(@PathVariable String relevant, @RequestBody String taskId) {
		
		Task task = taskService.createTaskQuery().taskId(taskId).singleResult();
		String processInstanceId = task.getProcessInstanceId();
		runtimeService.setVariable(processInstanceId, "Relevant", relevant);
					
        return new ResponseEntity<>(HttpStatus.OK);
    }
	
	@PostMapping(path = "/setFormatted/{formatted}", produces = "application/json")
    public @ResponseBody ResponseEntity setFormatted(@PathVariable String formatted, @RequestBody String taskId) {
		
		Task task = taskService.createTaskQuery().taskId(taskId).singleResult();
		String processInstanceId = task.getProcessInstanceId();
		runtimeService.setVariable(processInstanceId, "Formatted", formatted);
					
        return new ResponseEntity<>(HttpStatus.OK);
    }
	
	@GetMapping(path = "/AllReviewers", produces = "application/json")
    public @ResponseBody ResponseEntity<List<AppuserDto>> getAllReviewers() {
		
		List<Appuser> allAppusers = userService.getAll();
		List<AppuserDto> appuserDto = new ArrayList<>();
		
		for(Appuser c : allAppusers){
			String role = c.getRole();
			String id = c.getCity();
			if(role.equals("REVIEWER"))
				appuserDto.add(userService.mapToDTO(c));
		}
		
		return new ResponseEntity<List<AppuserDto>>(appuserDto, HttpStatus.OK);
    }	
	
	@PostMapping(path = "/addReviewers/{taskId}", produces = "application/json")
    public @ResponseBody ResponseEntity<Object> addReviewers(@RequestBody List<List<FieldDto>> dto, @PathVariable String taskId) {
		
		Task task = taskService.createTaskQuery().taskId(taskId).singleResult();
		String processInstanceId = task.getProcessInstanceId();
		
		List<Appuser> allAppusers = userService.getAll();
		List<Appuser> reviewers = new ArrayList<>();
		
		for(Appuser user: allAppusers) {
			for(List<FieldDto> fields: dto) {
				for(FieldDto field: fields) {
					if(user.getName().equals(field.getFieldValue())) {
						reviewers.add(user);
					}
				}
			}
		}
		
		List<String> usernames = new ArrayList<>();
		HashMap<String, Object> map = new HashMap<>();
		       
        for(Appuser user: reviewers) {
        	usernames.add(user.getUsername());
        }		
        
        runtimeService.setVariable(processInstanceId, "ListOfReviewers", usernames);
        runtimeService.setVariable(processInstanceId, "Reviewer", usernames.get(0));
		
        formService.submitTaskForm(taskId, map);
        return new ResponseEntity<>(HttpStatus.OK);
    }
	
	@PostMapping(path = "/setNumberOfReviewers/{num}", produces = "application/json")
    public @ResponseBody ResponseEntity setNumberOfReviewers(@PathVariable Long num, @RequestBody String taskId) {
		
		Task task = taskService.createTaskQuery().taskId(taskId).singleResult();
		String processInstanceId = task.getProcessInstanceId();
		runtimeService.setVariable(processInstanceId, "NumberReviewers", num);
					
        return new ResponseEntity<>(HttpStatus.OK);
    }
	
	@PostMapping(path = "/addReview", produces = "application/json")
    public @ResponseBody ResponseEntity AddReview(	@RequestParam("Comment") String comment,
													@RequestParam("Accept") String accept,
													@RequestParam("AcceptWithSmallUpdates") String smallupdates,
													@RequestParam("AcceptWithBigUpdates") String bigupdates,
													@RequestParam("Refuse") String refuse,
													@RequestParam("taskId") String taskId) throws IOException {

		Task task = taskService.createTaskQuery().taskId(taskId).singleResult();
		String processInstanceId = task.getProcessInstanceId();
		HashMap<String, Object> map = new HashMap<>();

		
        runtimeService.setVariable(processInstanceId, "reviewComment", comment);
        runtimeService.setVariable(processInstanceId, "reviewAccept", accept);
        runtimeService.setVariable(processInstanceId, "reviewSmallUpdates", smallupdates);
        runtimeService.setVariable(processInstanceId, "reviewBigUpdates", bigupdates);
        runtimeService.setVariable(processInstanceId, "reviewRefuse", refuse);
		
        formService.submitTaskForm(taskId, map);
		
        return new ResponseEntity<>(HttpStatus.OK);
    }
	
	@GetMapping(path = "/GetReviews/{processInstanceId}", produces = "application/json")
    public @ResponseBody ResponseEntity<List<List<String>>> GetReviews(@PathVariable String processInstanceId){
		        
        Object comments = runtimeService.getVariable(processInstanceId, "reviewComment");
        List<String> accepts = (List<String>)runtimeService.getVariable(processInstanceId, "reviewAccept");
        List<String> smallupdates = (List<String>)runtimeService.getVariable(processInstanceId, "reviewSmallUpdates");
        List<String> bigupdates = (List<String>)runtimeService.getVariable(processInstanceId, "reviewBigUpdates");
        List<String> refuses = (List<String>)runtimeService.getVariable(processInstanceId, "reviewRefuse");
        
        List<List<String>> lists = new ArrayList<>();
        lists.add(accepts);
        lists.add(smallupdates);
        lists.add(bigupdates);
        lists.add(refuses);
				
        return new ResponseEntity<>(lists, HttpStatus.OK);
    }
	
	@PostMapping(path = "/FinishedReview", produces = "application/json")
    public @ResponseBody ResponseEntity FinishedReview(	@RequestParam("AcceptedText") String accept,
													@RequestParam("AcceptedWithSmallUpdates") String smallupdates,
													@RequestParam("AcceptedWithBigUpdates") String bigupdates,
													@RequestParam("taskId") String taskId) throws IOException {

		Task task = taskService.createTaskQuery().taskId(taskId).singleResult();
		String processInstanceId = task.getProcessInstanceId();
		HashMap<String, Object> map = new HashMap<>();

		
        runtimeService.setVariable(processInstanceId, "AcceptedText", accept);
        runtimeService.setVariable(processInstanceId, "AcceptedWithSmallUpdates", smallupdates);
        runtimeService.setVariable(processInstanceId, "AcceptedWithBigUpdates", bigupdates);
		
        formService.submitTaskForm(taskId, map);
		
        return new ResponseEntity<>(HttpStatus.OK);
    }
	
	@PostMapping(path = "/saveChanges", produces = "application/json")
    public @ResponseBody ResponseEntity saveChanges(@RequestParam("ReplyComment") String reply,
													@RequestParam("Changes") String changes,
													@RequestParam("taskId") String taskId) throws IOException {

		Task task = taskService.createTaskQuery().taskId(taskId).singleResult();
		String processInstanceId = task.getProcessInstanceId();
		HashMap<String, Object> map = new HashMap<>();

		
        runtimeService.setVariable(processInstanceId, "ReplyComment", reply);
        runtimeService.setVariable(processInstanceId, "Changes", changes);

        formService.submitTaskForm(taskId, map);
		
        return new ResponseEntity<>(HttpStatus.OK);
    }
	
	@PostMapping(path = "/FinalAcceptance", produces = "application/json")
    public @ResponseBody ResponseEntity FinalAcceptance(@RequestParam("FinalAcceptance") String finalAcceptance,													
													@RequestParam("taskId") String taskId) throws IOException {

		Task task = taskService.createTaskQuery().taskId(taskId).singleResult();
		String processInstanceId = task.getProcessInstanceId();
		HashMap<String, Object> map = new HashMap<>();

		
        runtimeService.setVariable(processInstanceId, "FinalAcceptance", finalAcceptance);

        formService.submitTaskForm(taskId, map);
		
        return new ResponseEntity<>(HttpStatus.OK);
    }


}
