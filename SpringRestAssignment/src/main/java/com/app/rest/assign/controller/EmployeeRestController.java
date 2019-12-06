package com.app.rest.assign.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.app.rest.assign.exception.DepartmentNotFoundException;
import com.app.rest.assign.exception.EmployeeNotFoundException;
import com.app.rest.assign.model.Department;
import com.app.rest.assign.model.Employee;
import com.app.rest.assign.model.Salary;
import com.app.rest.assign.model.TechnicalSkill;
import com.app.rest.assign.service.DepartmentService;
import com.app.rest.assign.service.EmployeeService;
import com.app.rest.assign.service.SalaryService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(value = "Swagger@EmployeeResrController", description = "Rest API Related to Employee Entity")
@RestController
@RequestMapping(value = "/assignment/employee")
public class EmployeeRestController {

	@Autowired
	private EmployeeService employeeService;

	@Autowired
	private DepartmentService departmentService;

	@Autowired
	private SalaryService salaryService;

	@ApiOperation(value = "Get specific employee in the system", response = Employee.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success|OK"),
			@ApiResponse(code = 404, message = "Failure|NOT_FOUND") })
	@GetMapping(value = "/{employeeId}", produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE,
			"application/vnd.yash.api.v1+json", "application/vnd.yash.api.v1+xml" })
	public ResponseEntity<Employee> getEmployeeById(@PathVariable Integer employeeId) {
		Employee employeeResponse = employeeService.getEmployeeById(employeeId);
		if (employeeResponse == null)
			throw new EmployeeNotFoundException("Employee Not Found");
		else
			return new ResponseEntity<>(employeeResponse, HttpStatus.OK);
	}

	@ApiOperation(value = "enroll employee in the system", response = Employee.class)
	@ApiResponses(value = { @ApiResponse(code = 201, message = "Success|CREATED") })
	@PostMapping(value = "/{departmentId}", consumes = { MediaType.APPLICATION_XML_VALUE,
			MediaType.APPLICATION_JSON_VALUE, "application/vnd.yash.api.v1+json",
			"application/vnd.yash.api.v1+xml" }, produces = { MediaType.APPLICATION_XML_VALUE,
					MediaType.APPLICATION_JSON_VALUE, "application/vnd.yash.api.v1+json",
					"application/vnd.yash.api.v1+xml" })
	public ResponseEntity<Employee> enrollEmployee(@PathVariable Integer departmentId, @RequestBody Employee employee) {
		employee.setDepartment(departmentService.getDepartmentById(departmentId));
		Employee employeeResponse = employeeService.enrollEmployee(employee);
		return new ResponseEntity<>(employeeResponse, HttpStatus.CREATED);
	}

	@ApiOperation(value = "Get all employees in the system", response = Iterable.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success|OK"), })
	@GetMapping(/* value = "/assignment/employee", */ produces = { MediaType.APPLICATION_XML_VALUE,
			MediaType.APPLICATION_JSON_VALUE, "application/vnd.yash.api.v1+json", "application/vnd.yash.api.v1+xml" })
	public ResponseEntity<List<Employee>> getAllEmployees() {
		return new ResponseEntity<>(employeeService.getAllEmployee(), HttpStatus.OK);
	}

	@ApiOperation(value = "Get all employees in department from system", response = Iterable.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success|OK"),
			@ApiResponse(code = 404, message = "Failure|NOT_FOUND") })
	@GetMapping(value = "/dept/{departmentId}", produces = { MediaType.APPLICATION_XML_VALUE,
			MediaType.APPLICATION_JSON_VALUE, "application/vnd.yash.api.v1+json", "application/vnd.yash.api.v1+xml" })
	public ResponseEntity<List<Employee>> fetchEmployeesFromDepartment(@PathVariable Integer departmentId) {

		List<Employee> employees = new ArrayList<>();
		Department department = departmentService.getDepartmentById(departmentId);
		if (department == null)
			throw new DepartmentNotFoundException("Department Not Present");
		else {
			employees = employeeService.getEmpolyeeInDepartment(departmentId);
			if (employees.isEmpty())
				throw new EmployeeNotFoundException("Employee Not Present In Department");
			else
				return new ResponseEntity<>(employees, HttpStatus.OK);
		}

	}

	@ApiOperation(value = "update employee details  in the system", response = Employee.class)
	@ApiResponses(value = { @ApiResponse(code = 201, message = "Success|CREATED") })
	@PutMapping(value = "/{employeeId}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Employee> updateEmployee(@PathVariable Integer employeeId, @RequestBody Employee employee) {
		Employee employeeResponse = employeeService.updateEmployee(employee, employeeId);
		return new ResponseEntity<>(employeeResponse, HttpStatus.CREATED);
	}

	@ApiOperation(value = "update employee location  in the system", response = Employee.class)
	@ApiResponses(value = { @ApiResponse(code = 201, message = "Success|CREATED") })
	@PatchMapping(value = "/{employeeId}")
	public ResponseEntity<Employee> updateEmployeeLocation(@PathVariable Integer employeeId,
			@RequestParam String location) {
		Employee employeeResponse = employeeService.updateEmployeeLocation(location, employeeId);
		return new ResponseEntity<>(employeeResponse, HttpStatus.CREATED);
	}

	@ApiOperation(value = "delete employee from the system", response = String.class)
	@ApiResponses(value = { @ApiResponse(code = 204, message = "Success|NO_CONTENT") })
	@DeleteMapping(value = "/{employeeId}")
	public ResponseEntity<String> deleteEmployee(@PathVariable Integer employeeId) {
		return new ResponseEntity<>(employeeService.deleteEmployee(employeeId), HttpStatus.NO_CONTENT);
	}

	@GetMapping(value = "/salary/{employeeId}")
	public ResponseEntity<Salary> getSalaryOfEmployee(@PathVariable Integer employeeId) {
		return new ResponseEntity<>(employeeService.getSalaryOfEmployee(employeeId), HttpStatus.OK);
	}

	@PostMapping(value = "/salary/{employeeId}")
	public ResponseEntity<Salary> addSalaryOfEmployee(@PathVariable Integer employeeId, @RequestBody Salary salary) {
		Employee employee = employeeService.getEmployeeById(employeeId);
		Salary savedSalary = salaryService.addEmployeeSalary(salary);
		employee.setSalary(savedSalary);
		employeeService.enrollEmployee(employee);
		return new ResponseEntity<>(savedSalary, HttpStatus.CREATED);
	}

	@PostMapping(value = "/skills/{employeeId}")
	public ResponseEntity<TechnicalSkill> addSkillSet(@PathVariable Integer employeeId,
			@RequestBody TechnicalSkill technicalSkill) {
		Employee employee = employeeService.getEmployeeById(employeeId);
		technicalSkill.setEmployee(employee);
		return new ResponseEntity<>(employeeService.addTechnicalSkill(technicalSkill), HttpStatus.OK);
	}

	@GetMapping(value = "/skills/{employeeId}")
	public ResponseEntity<List<TechnicalSkill>> getSkillsOfEmployee(@PathVariable Integer employeeId) {
		return new ResponseEntity<>(employeeService.getSkillsOfEmployee(employeeId), HttpStatus.OK);
	}
}
