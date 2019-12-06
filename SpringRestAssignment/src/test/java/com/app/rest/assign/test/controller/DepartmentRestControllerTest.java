package com.app.rest.assign.test.controller;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;

import com.app.rest.assign.controller.DepartmentRestController;
import com.app.rest.assign.model.Department;
import com.app.rest.assign.model.Employee;
import com.app.rest.assign.service.DepartmentService;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringRunner.class)
@WebMvcTest(value = DepartmentRestController.class)
public class DepartmentRestControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private DepartmentService mockDepartmentService;

	@Test
	public void shouldReturnDepartmentWithEmployeeByProvidingEmployeeIdInJson() throws Exception {
		Employee mockEmployeeOne = new Employee(1, "name", "email", "designation", "contactNumber", "location");
		Employee mockEemployeeTwo = new Employee(2, "name", "email", "designation", "contactNumber", "location");
		List<Employee> mockEmployees = new ArrayList<Employee>();
		mockEmployees.add(mockEmployeeOne);
		mockEmployees.add(mockEemployeeTwo);
		Department mockDepartment = new Department(1, "departmentName", "departmentLocation", mockEmployees);
		when(mockDepartmentService.getDepartmentById(1)).thenReturn(mockDepartment);
		RequestBuilder requestBuilder = get("/assignment/department/1").accept(MediaType.APPLICATION_JSON);
		MvcResult mvcResult = this.mockMvc.perform(requestBuilder).andReturn();
		System.out.println(mvcResult.getResponse().getContentAsString());
		verify(mockDepartmentService, times(1)).getDepartmentById(1);

	}

	@Test
	public void shouldReturnDepartmentWithEmployeeByProvidingEmployeeIdInXml() throws Exception {
		Employee mockEmployeeOne = new Employee(1, "name", "email", "designation", "contactNumber", "location");
		Employee mockEemployeeTwo = new Employee(2, "name", "email", "designation", "contactNumber", "location");
		List<Employee> mockEmployees = new ArrayList<Employee>();
		mockEmployees.add(mockEmployeeOne);
		mockEmployees.add(mockEemployeeTwo);
		Department mockDepartment = new Department(1, "departmentName", "departmentLocation", mockEmployees);
		when(mockDepartmentService.getDepartmentById(1)).thenReturn(mockDepartment);
		RequestBuilder requestBuilder = get("/assignment/department/1").accept(MediaType.APPLICATION_XML);
		MvcResult mvcResult = this.mockMvc.perform(requestBuilder).andReturn();
		System.out.println(mvcResult.getResponse().getContentAsString());
		verify(mockDepartmentService, times(1)).getDepartmentById(1);
	}

	@Test
	public void shouldcreateDepartment() throws Exception {
		Department departmentBody = new Department(null,"departmentName", "departmentLocation");
		Department departmentResponse = new Department(1, "departmentName", "departmentLocation");
		when(mockDepartmentService.createDepartment(departmentBody)).thenReturn(departmentResponse);

		ObjectMapper mapper = new ObjectMapper();
		RequestBuilder requestBuilder = post("/assignment/department/").accept(MediaType.APPLICATION_JSON)
				.content(mapper.writeValueAsString(departmentBody)).contentType(MediaType.APPLICATION_JSON);
		MvcResult mvcResult = this.mockMvc.perform(requestBuilder).andReturn();
		assertEquals(HttpStatus.CREATED.value(), mvcResult.getResponse().getStatus());

		System.out.println(mvcResult.getResponse().getContentAsString());

		verify(mockDepartmentService, times(1)).createDepartment(departmentBody);
	}
	
	
}
