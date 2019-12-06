package com.app.rest.assign.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.rest.assign.model.Salary;
import com.app.rest.assign.repository.SalaryRepository;

@Service
@Transactional
public class SalaryServiceImpl implements SalaryService{

	@Autowired
	private SalaryRepository salaryRepository;
	
	@Override
	public Salary addEmployeeSalary(Salary salary) {
		return salaryRepository.save(salary);
	}

}
