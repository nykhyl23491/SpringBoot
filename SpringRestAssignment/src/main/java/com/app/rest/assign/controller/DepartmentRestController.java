package com.app.rest.assign.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.rest.assign.model.Department;
import com.app.rest.assign.service.DepartmentService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
@Api(value = "Swagger@DepartmentResrController",description = "Rest API Related to Department Entity")
@RestController
@RequestMapping(value = "/assignment/department")
public class DepartmentRestController {

	@Autowired
	private DepartmentService departmentService;

	@ApiOperation(value = "Create new department in the system",response = Department.class)
	@ApiResponses(value = {
			@ApiResponse(code = 201,message = "Success|CREATED"),
	})
	@PostMapping(consumes = { MediaType.APPLICATION_XML_VALUE,
			MediaType.APPLICATION_JSON_VALUE, "application/vnd.yash.api.v1+json",
			"application/vnd.yash.api.v1+xml" }, produces = { MediaType.APPLICATION_XML_VALUE,
					MediaType.APPLICATION_JSON_VALUE, "application/vnd.yash.api.v1+json",
					"application/vnd.yash.api.v1+xml" })
	public ResponseEntity<Department> createDepartment(@RequestBody Department department) {
		Department deptResponse = departmentService.createDepartment(department);
		return new ResponseEntity<>(deptResponse, HttpStatus.CREATED);
	}

	@ApiOperation(value = "Get specific department in the system",response = Department.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200,message = "Success|OK"),
			@ApiResponse(code = 404,message = "Failure|NOT_FOUND")
	})
	@GetMapping(value = "/{departmentId}", produces = { MediaType.APPLICATION_XML_VALUE,
			MediaType.APPLICATION_JSON_VALUE, "application/vnd.yash.api.v1+json", "application/vnd.yash.api.v1+xml" })
	public ResponseEntity<Department> fetchDepartmnet(@PathVariable Integer departmentId) {
		Department deptResponse = departmentService.getDepartmentById(departmentId);
		return new ResponseEntity<>(deptResponse, HttpStatus.OK);
	}
}
