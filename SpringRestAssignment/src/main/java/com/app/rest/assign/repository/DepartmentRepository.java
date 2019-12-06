package com.app.rest.assign.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.rest.assign.model.Department;

public interface DepartmentRepository extends JpaRepository<Department, Integer>{

}
