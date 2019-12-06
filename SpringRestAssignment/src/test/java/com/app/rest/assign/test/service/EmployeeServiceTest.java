package com.app.rest.assign.test.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.app.rest.assign.model.Department;
import com.app.rest.assign.model.Employee;
import com.app.rest.assign.repository.EmployeeRepository;
import com.app.rest.assign.service.EmployeeServiceImpl;

@RunWith(MockitoJUnitRunner.class)
public class EmployeeServiceTest {

	@InjectMocks
	private EmployeeServiceImpl employeeService;
	
	
	
	@Mock
	private EmployeeRepository mockEmployeeRepository;
	
	@Test
	public void shouldTestEnrollEmployee()
	{
		Department department = new Department(1, "departmentName", "departmentLocation");
		Employee employeeInput = new Employee(null, "employeeName", "employeeEmail", "employeeDesignation", "employeeContactNumber", "employeeLocation", department);
		Employee employeeOutput = new Employee(1, "employeeName", "employeeEmail", "employeeDesignation", "employeeContactNumber", "employeeLocation", department);
		when(mockEmployeeRepository.save(employeeInput)).thenReturn(employeeOutput);
		employeeService.enrollEmployee(employeeInput);
		verify(mockEmployeeRepository,times(1)).save(employeeInput);
	}
	
	@Test
	public void shouldReturnEmployeeIfPresentByProvidingEmployeeId()
	{
			Integer employeeId =1;
			Employee employee = new Employee(1, "employeeName", "employeeEmail", "employeeDesignation", "employeeContactNumber", "employeeLocation");
			Optional<Employee> employeeOutput = Optional.of(employee);
			when(mockEmployeeRepository.findById(employeeId)).thenReturn(employeeOutput);
			employeeService.getEmployeeById(employeeId);
			verify(mockEmployeeRepository,times(1)).findById(employeeId);
	}
	
	@Test
	public void shouldReturnNullIfEmployeeNotPresent()
	{
		Integer employeeId =100;
		Optional<Employee> employeeOutput = Optional.ofNullable(null);
		when(mockEmployeeRepository.findById(employeeId)).thenReturn(employeeOutput);
		employeeService.getEmployeeById(employeeId);
		verify(mockEmployeeRepository,times(1)).findById(employeeId);
	}
	
	@Test
	public void shouldReturnAllEmployeeesPresent()
	{
		List<Employee> employees = new ArrayList<Employee>();
		employees.add(new Employee(1, "name", "email", "designation", "contactNumber", "location"));
		employees.add(new Employee(2, "name", "email", "designation", "contactNumber", "location"));
		when(mockEmployeeRepository.findAll()).thenReturn(employees);
		employeeService.getAllEmployee();
		verify(mockEmployeeRepository,times(1)).findAll();
	}

	@Test
	public void shouldReturnEmployeePresentInGivenDepartment()
	{
		Integer departmentId =1;
		List<Employee> employees = new ArrayList<Employee>();
		employees.add(new Employee(1, "name", "email", "designation", "contactNumber", "location"));
		employees.add(new Employee(2, "name", "email", "designation", "contactNumber", "location"));
		when(mockEmployeeRepository.findEmployeesInDepartment(departmentId)).thenReturn(employees);
		employeeService.getEmpolyeeInDepartment(departmentId);
		verify(mockEmployeeRepository,times(1)).findEmployeesInDepartment(departmentId);
	}
	
	@Test
	public void shouldUpdateEmployee()
	{
		Integer employeeId =1;
		Employee employeeToUpdate = new Employee(employeeId,"name", "email", "designation", "contactNumber", "location");
		when(mockEmployeeRepository.getOne(employeeId)).thenReturn(employeeToUpdate);
		Employee employeeUpdated = new Employee(employeeId,"name1", "email1", "designation1", "contactNumber1", "location1");
		when(mockEmployeeRepository.save(employeeToUpdate)).thenReturn(employeeUpdated);
		employeeService.updateEmployee(employeeToUpdate, employeeId);
		verify(mockEmployeeRepository,times(1)).save(employeeToUpdate);
	}
	
	@Test
	public void shouldUpdateEmployeeLocation()
	{
		Integer employeeId =1;
		String location = "USA";
		Employee employeeToUpdate = new Employee(1,"name", "email", "designation", "contactNumber", "location");
		when(mockEmployeeRepository.getOne(employeeId)).thenReturn(employeeToUpdate);
		Employee employeeUpdated = new Employee(employeeId,"name1", "email1", "designation1", "contactNumber1", location);
		when(mockEmployeeRepository.save(employeeToUpdate)).thenReturn(employeeUpdated);
		employeeService.updateEmployeeLocation(location, employeeId);
		verify(mockEmployeeRepository,times(1)).save(employeeToUpdate);
	}
	
	@Test
	public void shouldDeleteEmployee()
	{
		Integer employeeId =1;
		String message = "Employee Deleted";
		doNothing().when(mockEmployeeRepository).deleteById(employeeId);
		assertEquals(message,employeeService.deleteEmployee(employeeId));
		verify(mockEmployeeRepository,times(1)).deleteById(employeeId);
	}
}


