package com.app.rest.assign.test.service;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.app.rest.assign.model.Department;
import com.app.rest.assign.repository.DepartmentRepository;
import com.app.rest.assign.service.DepartmentServiceImpl;

@RunWith(MockitoJUnitRunner.class)
public class DepartmentServiceTest {
	
	@InjectMocks
	DepartmentServiceImpl departmentServiceImpl;
	
	@Mock
	DepartmentRepository mockDepartmentRepository;
	
	@Test
	public void shouldCreateDepatyment()
	{
		Department departmentInput = new Department(null, "departmentName", "departmentLocation");
		Department departmentOutput = new Department(1, "departmentName", "departmentLocation");
		when(mockDepartmentRepository.save(departmentInput)).thenReturn(departmentOutput);
		departmentServiceImpl.createDepartment(departmentInput);
		verify(mockDepartmentRepository,times(1)).save(departmentInput);
	}
	
	@Test
	public void shouldRetunDepartmentByProvidingDepartmentId()
	{
		Integer departmentId =1;
		Department department = new Department(1, "departmentName", "departmentLocation");
		Optional<Department> departmentOutput = Optional.of(department);
		when(mockDepartmentRepository.findById(departmentId)).thenReturn(departmentOutput);
		departmentServiceImpl.getDepartmentById(departmentId);
		verify(mockDepartmentRepository,times(1)).findById(departmentId);
	}

	@Test
	public void shouldRetunNullIfDepartmentIsNotPresentByProvidingDepartmentId()
	{
		Integer departmentId =100;
		Optional<Department> departmentOutput = Optional.ofNullable(null);
		when(mockDepartmentRepository.findById(departmentId)).thenReturn(departmentOutput);
		departmentServiceImpl.getDepartmentById(departmentId);
		verify(mockDepartmentRepository,times(1)).findById(departmentId);
	}

}
