package com.app.rest.assign.service;

import com.app.rest.assign.model.Department;

public interface DepartmentService {
	public Department createDepartment(Department department);
	public Department getDepartmentById(Integer departmentId);
	

}
