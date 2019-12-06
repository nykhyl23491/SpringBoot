package com.app.rest.assign.service;

import java.util.List;

import com.app.rest.assign.model.Employee;
import com.app.rest.assign.model.Salary;
import com.app.rest.assign.model.TechnicalSkill;

public interface EmployeeService {

	public Employee enrollEmployee(Employee employee);

	public Employee getEmployeeById(Integer employeeId);

	public List<Employee> getAllEmployee();

	public List<Employee> getEmpolyeeInDepartment(Integer departmentId);

	public Employee updateEmployee(Employee employee, Integer employeeId);

	public Employee updateEmployeeLocation(String location, Integer employeeId);

	public String deleteEmployee(Integer employeeId);
	
	public Salary getSalaryOfEmployee(Integer employeeId);
	
	public List<TechnicalSkill> getSkillsOfEmployee(Integer employeeId);
	
	public TechnicalSkill addTechnicalSkill(TechnicalSkill skill);

}
