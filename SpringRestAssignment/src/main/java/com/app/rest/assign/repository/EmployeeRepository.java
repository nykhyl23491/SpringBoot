package com.app.rest.assign.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.app.rest.assign.model.Employee;
import com.app.rest.assign.model.Salary;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

	@Query(value = "from Employee e where e.department.departmentId=:departmentId")
	public List<Employee> findEmployeesInDepartment(int departmentId);
	
	@Query(value = "select s from Salary s left outer join Employee e on s.salaryId = e.salary.salaryId where e.employeeId=:employeeId")
	public Salary getSalaryOfEmployee(Integer employeeId);
	
	
	
	
}
