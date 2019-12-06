package com.app.rest.assign.test.controller;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.xpath;

import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;

import org.junit.Before;
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
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.app.rest.assign.controller.EmployeeRestController;
import com.app.rest.assign.model.Department;
import com.app.rest.assign.model.Employee;
import com.app.rest.assign.service.DepartmentService;
import com.app.rest.assign.service.EmployeeService;
import com.app.rest.assign.service.SalaryService;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringRunner.class)
@WebMvcTest(value = EmployeeRestController.class)
public class EmployeeRestControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private WebApplicationContext context;

	@MockBean
	private EmployeeService mockEmployeeService;
	
	@MockBean
	private DepartmentService mockDepartmentService;
	
	@MockBean
	private SalaryService mockSalaryService;

	@Before
	public void setUp() {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(this.context).build();
	}

	@Test
	public void shouldReturnListOfEmployeeInJsonFormat() throws Exception {
		List<Employee> mockEmployees = new ArrayList<Employee>();
		mockEmployees.add(new Employee(1, "name", "email", "designation", "contactNumber", "location"));
		mockEmployees.add(new Employee(2, "name", "email", "designation", "contactNumber", "location"));
		when(mockEmployeeService.getAllEmployee()).thenReturn(mockEmployees);
		RequestBuilder requestBuilder = get("/assignment/employee").accept(MediaType.APPLICATION_JSON);
		MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();
		assertEquals(HttpStatus.OK.value(), mvcResult.getResponse().getStatus());
		System.out.println(mvcResult.getResponse().getContentAsString());
		verify(mockEmployeeService, times(1)).getAllEmployee();
	}

	@Test
	public void shouldReturnListOfEmployeeInXmlFormat() throws Exception {
		List<Employee> mockEmployees = new ArrayList<Employee>();
		mockEmployees.add(new Employee(1, "name", "email", "designation", "contactNumber", "location"));
		mockEmployees.add(new Employee(2, "name", "email", "designation", "contactNumber", "location"));
		when(mockEmployeeService.getAllEmployee()).thenReturn(mockEmployees);
		RequestBuilder requestBuilder = get("/assignment/employee").accept(MediaType.APPLICATION_XML);
		MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();
		System.out.println(mvcResult.getResponse().getContentAsString());
		verify(mockEmployeeService, times(1)).getAllEmployee();
	}

	@Test
	public void shouldReturnEmployeeByProvidingEmployeeId() throws Exception {
		Employee employee = new Employee(1, "employeeName", "employeeEmail", "employeeDesignation",
				"employeeContactNumber", "employeeLocation");
		when(mockEmployeeService.getEmployeeById(1)).thenReturn(employee);

		RequestBuilder requestBuilderJson = get("/assignment/employee/1").accept(MediaType.APPLICATION_JSON);
		this.mockMvc.perform(requestBuilderJson).andExpect(status().isOk()).andExpect(jsonPath("$.employeeId").exists())
				.andExpect(jsonPath("$.employeeName").exists()).andExpect(jsonPath("$.employeeEmail").exists())
				.andExpect(jsonPath("$.employeeDesignation").exists())
				.andExpect(jsonPath("$.employeeContactNumber").exists())
				.andExpect(jsonPath("$.employeeLocation").exists());

		RequestBuilder requestBuilderXml = get("/assignment/employee/1").accept(MediaType.APPLICATION_XML);
		this.mockMvc.perform(requestBuilderXml).andExpect(status().isOk())
				.andExpect(xpath("Resource/employeeId").exists()).andExpect(xpath("Resource/employeeName").exists())
				.andExpect(xpath("Resource/employeeEmail").exists())
				.andExpect(xpath("Resource/employeeDesignation").exists())
				.andExpect(xpath("Resource/employeeContactNumber").exists())
				.andExpect(xpath("Resource/employeeLocation").exists());

		verify(mockEmployeeService, times(2)).getEmployeeById(1);
	}

	@Test
	public void shouldReturnExceptionResponseWhenEmployeeIdIsUnknown() throws Exception {
		this.mockMvc.perform(get("/assignment/employee/100")).andExpect(status().isNotFound())
				.andExpect(jsonPath("$.statusCode", is(HttpStatus.NOT_FOUND.value())))
				.andExpect(jsonPath("$.errorMessage", is("Employee Not Found")))
				.andExpect(jsonPath("$.requestURI", is("/assignment/employee/100")));
	}

	@Test
	public void shouldReturnExceptionResponseWhenEmployeeIdIsInvalid() throws Exception {
		this.mockMvc.perform(get("/assignment/employee/www")).andExpect(status().isBadRequest())
				.andExpect(jsonPath("$.statusCode", is(HttpStatus.BAD_REQUEST.value())))
				.andExpect(jsonPath("$.errorMessage", is("Bad Request")))
				.andExpect(jsonPath("$.requestURI", is("/assignment/employee/www")));
	}

	@Test
	public void shouldCreateEmployee() throws Exception {
//		Department department = new Department();
//		department.setDepartmentId(1);
		Employee employeeBody = new Employee(null, "employeeName", "employeeEmail", "employeeDesignation",
				"employeeContactNumber", "employeeLocation");
		Employee employeeResponse = new Employee(1, "employeeName", "employeeEmail", "employeeDesignation",
				"employeeContactNumber", "employeeLocation");
		when(mockEmployeeService.enrollEmployee(employeeBody)).thenReturn(employeeResponse);

		ObjectMapper objectMapper = new ObjectMapper();
		RequestBuilder requestBuilderJson = post("/assignment/employee/1").accept(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(employeeBody)).contentType(MediaType.APPLICATION_JSON);

		this.mockMvc.perform(requestBuilderJson).andExpect(status().isCreated())
				.andExpect(jsonPath("$.employeeId").exists()).andExpect(jsonPath("$.employeeName").exists())
				.andExpect(jsonPath("$.employeeEmail").exists()).andExpect(jsonPath("$.employeeDesignation").exists())
				.andExpect(jsonPath("$.employeeContactNumber").exists())
				.andExpect(jsonPath("$.employeeLocation").exists());

		JAXBContext jaxbContext = JAXBContext.newInstance(Employee.class);
		Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
		jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
		StringWriter stringWriter = new StringWriter();
		jaxbMarshaller.marshal(employeeBody, stringWriter);
		System.out.println(stringWriter.toString());

		RequestBuilder requestBuilderXml = post("/assignment/employee/1").accept(MediaType.APPLICATION_XML)
				.content(stringWriter.toString()).contentType(MediaType.APPLICATION_XML);
		this.mockMvc.perform(requestBuilderXml).andExpect(status().isCreated())
				.andExpect(xpath("Employee/employeeId").exists()).andExpect(xpath("Employee/employeeName").exists())
				.andExpect(xpath("Employee/employeeEmail").exists())
				.andExpect(xpath("Employee/employeeDesignation").exists())
				.andExpect(xpath("Employee/employeeContactNumber").exists())
				.andExpect(xpath("Employee/employeeLocation").exists());

		verify(mockEmployeeService, times(2)).enrollEmployee(employeeBody);
	}

	@Test
	public void shouldDeleteEmployeeWhenPrvidingEmployeeId() throws Exception {
		when(mockEmployeeService.deleteEmployee(1)).thenReturn("Employee Deleted");
		RequestBuilder requestBuilderDelete = delete("/assignment/employee/1");
		MvcResult mvcResult = this.mockMvc.perform(requestBuilderDelete).andReturn();
		assertEquals(HttpStatus.NO_CONTENT.value(), mvcResult.getResponse().getStatus());
		assertEquals("Employee Deleted", mvcResult.getResponse().getContentAsString());
		verify(mockEmployeeService, times(1)).deleteEmployee(1);
	}

	@Test
	public void shouldReturnEmployeesPresentInDepartment() throws Exception {
		
		Department department = new Department(1, "departmentName", "departmentLocation");
		List<Employee> employees = new ArrayList<Employee>();
		employees.add(new Employee(1, "name", "email", "designation", "contactNumber", "location"));
		employees.add(new Employee(2, "name", "email", "designation", "contactNumber", "location"));
		when(mockDepartmentService.getDepartmentById(1)).thenReturn(department);
		when(mockEmployeeService.getEmpolyeeInDepartment(1)).thenReturn(employees);

		RequestBuilder requestBuilderJson = get("/assignment/employee/dept/1").accept(MediaType.APPLICATION_JSON);
		this.mockMvc.perform(requestBuilderJson).andExpect(status().isOk())
				.andExpect(jsonPath("$[0].employeeId").exists()).andExpect(jsonPath("$[0].employeeName").exists())
				.andExpect(jsonPath("$[0].employeeEmail").exists())
				.andExpect(jsonPath("$[0].employeeDesignation").exists())
				.andExpect(jsonPath("$[0].employeeContactNumber").exists())
				.andExpect(jsonPath("$[0].employeeLocation").exists());

		RequestBuilder requestBuilderXml = get("/assignment/employee/dept/1").accept(MediaType.APPLICATION_XML);
		this.mockMvc.perform(requestBuilderXml).andExpect(status().isOk())
				.andExpect(xpath("List/item/employeeId").exists()).andExpect(xpath("List/item/employeeName").exists())
				.andExpect(xpath("List/item/employeeEmail").exists())
				.andExpect(xpath("List/item/employeeDesignation").exists())
				.andExpect(xpath("List/item/employeeContactNumber").exists())
				.andExpect(xpath("List/item/employeeLocation").exists());

		verify(mockEmployeeService, times(2)).getEmpolyeeInDepartment(1);
	}
	
	@Test
	public void shouldReturnExceptionResponseWhenDepartmentIsUnknown() throws Exception {
		
		this.mockMvc.perform(get("/assignment/employee/dept/100")).andExpect(status().isNotFound())
				.andExpect(jsonPath("$.statusCode", is(HttpStatus.NOT_FOUND.value())))
				.andExpect(jsonPath("$.errorMessage", is("Department Not Present")))
				.andExpect(jsonPath("$.requestURI", is("/assignment/employee/dept/100")));
	}
	
	@Test
	public void shouldReturnExceptionResponseWhenEmployeeNotPresentInDepartment() throws Exception {
		when(mockDepartmentService.getDepartmentById(5)).thenReturn(new Department(5, "departmentName", "departmentLocation"));
		List<Employee> employees = new ArrayList<Employee>();
		when(mockEmployeeService.getEmpolyeeInDepartment(5)).thenReturn(employees);
		this.mockMvc.perform(get("/assignment/employee/dept/5")).andExpect(status().isNotFound()).andDo(print())
				.andExpect(jsonPath("$.statusCode", is(HttpStatus.NOT_FOUND.value())))
				.andExpect(jsonPath("$.errorMessage", is("Employee Not Present In Department")))
				.andExpect(jsonPath("$.requestURI", is("/assignment/employee/dept/5")));
	}

	@Test
	public void shouldReturnExceptionResponseWhenDepartmentIdIsInvalid() throws Exception {
		this.mockMvc.perform(get("/assignment/employee/dept/www")).andExpect(status().isBadRequest())
				.andExpect(jsonPath("$.statusCode", is(HttpStatus.BAD_REQUEST.value())))
				.andExpect(jsonPath("$.errorMessage", is("Bad Request")))
				.andExpect(jsonPath("$.requestURI", is("/assignment/employee/dept/www")));
	}


	@Test
	public void shouldUpdateEmployee() throws Exception {
		Employee employeeBody = new Employee(null, "employeeName", "employeeEmail", "employeeDesignation",
				"employeeContactNumber", "employeeLocation");
		Employee employeeResponse = new Employee(1, "employeeName", "employeeEmail", "employeeDesignation",
				"employeeContactNumber", "employeeLocation");
		when(mockEmployeeService.updateEmployee(employeeBody, 1)).thenReturn(employeeResponse);

		ObjectMapper objectMapper = new ObjectMapper();
		RequestBuilder requestBuilderJson = put("/assignment/employee/1").accept(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(employeeBody)).contentType(MediaType.APPLICATION_JSON);

		this.mockMvc.perform(requestBuilderJson).andExpect(status().isCreated())
				.andExpect(jsonPath("$.employeeId").exists()).andExpect(jsonPath("$.employeeName").exists())
				.andExpect(jsonPath("$.employeeEmail").exists()).andExpect(jsonPath("$.employeeDesignation").exists())
				.andExpect(jsonPath("$.employeeContactNumber").exists())
				.andExpect(jsonPath("$.employeeLocation").exists());

		verify(mockEmployeeService, times(1)).updateEmployee(employeeBody, 1);
	}
	
	@Test
	public void shouldUpdateEmployeeLocation() throws Exception
	{
		String location = "USA";
		Employee employee = new Employee(1, "employeeName", "employeeEmail", "employeeDesignation", "employeeContactNumber", location);
		when(mockEmployeeService.updateEmployeeLocation(location, 1)).thenReturn(employee);
		
		RequestBuilder requestBuilder = patch("/assignment/employee/1?location="+location).accept(MediaType.APPLICATION_JSON);
		this.mockMvc.perform(requestBuilder).andExpect(status().isCreated())
		.andExpect(jsonPath("$.employeeId").exists()).andExpect(jsonPath("$.employeeName").exists())
		.andExpect(jsonPath("$.employeeEmail").exists()).andExpect(jsonPath("$.employeeDesignation").exists())
		.andExpect(jsonPath("$.employeeContactNumber").exists())
		.andExpect(jsonPath("$.employeeLocation").exists());
		
		verify(mockEmployeeService, times(1)).updateEmployeeLocation(location, 1);

	}
}
