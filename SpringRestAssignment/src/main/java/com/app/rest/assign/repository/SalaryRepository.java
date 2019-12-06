package com.app.rest.assign.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.rest.assign.model.Salary;

public interface SalaryRepository extends JpaRepository<Salary, Integer>{
	
}
