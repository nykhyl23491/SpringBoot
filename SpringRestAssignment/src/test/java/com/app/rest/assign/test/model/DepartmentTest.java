package com.app.rest.assign.test.model;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

import com.app.rest.assign.model.Department;

import nl.jqno.equalsverifier.EqualsVerifier;
import nl.jqno.equalsverifier.Warning;

@RunWith(MockitoJUnitRunner.class)

public class DepartmentTest {
	
	@InjectMocks
	private Department department = new Department();
	
	@Test
	public void testDepartmentToString() {
		Department department = new Department(1, "departmentName", "departmentLocation");
		String str = department.toString();
		assertEquals(department.toString(), str);
	}
	
	@Test
	public void testDepartmentEqualsAndHashcode() {
		Department department1 = new Department(1, "departmentName1", "departmentLocation1");
		Department department2 = new Department(2, "departmentName2", "departmentLocation2");
		System.out.println("---------" + department1.hashCode());
		System.out.println("---------" + department2.hashCode());
		EqualsVerifier.forExamples(department1, department2).suppress(Warning.NONFINAL_FIELDS).usingGetClass().verify();
	}
}
