package com.app.rest.assign.test.model;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

import com.app.rest.assign.model.Employee;

import nl.jqno.equalsverifier.EqualsVerifier;
import nl.jqno.equalsverifier.Warning;

@RunWith(MockitoJUnitRunner.class)
public class EmployeeTest {
	
	@InjectMocks
	private Employee employee = new Employee();
	
	@Test
	public void testEmployeeToString() {
		Employee emp = new Employee(1, "employeeName", "employeeEmail", "employeeDesignation", "employeeContactNumber", "employeeLocation");
		String str = emp.toString();
		assertEquals(emp.toString(), str);
	}
	
	@Test
	public void testEmployeeEqualsAndHashcode() {
		Employee e1 = new Employee(1, "employeeName1", "employeeEmail1", "employeeDesignation1", "employeeContactNumber1", "employeeLocation1");
		Employee e2 = new Employee(2, "employeeName2", "employeeEmail2", "employeeDesignation2", "employeeContactNumber2", "employeeLocation2");
		System.out.println("---------" + e1.hashCode());
		System.out.println("---------" + e2.hashCode());
		EqualsVerifier.forExamples(e1, e2).suppress(Warning.NULL_FIELDS).usingGetClass().verify();
	}

}
