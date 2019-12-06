//package com.app.rest.assign.controller;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.MediaType;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.app.rest.assign.model.TechnicalSkill;
//import com.app.rest.assign.service.SkillService;
//
//@RestController
//@RequestMapping(value = "/assignment/skill")
//public class SkillController {
//
//	@Autowired
//	private SkillService skillService;
//
//	@PostMapping(consumes = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
//	public ResponseEntity<TechnicalSkill> addTechnicalSkill(@RequestBody TechnicalSkill technicalSkill) {
//		return new ResponseEntity<>(skillService.addTechnicalSkill(technicalSkill), HttpStatus.CREATED);
//	}
//
//}
