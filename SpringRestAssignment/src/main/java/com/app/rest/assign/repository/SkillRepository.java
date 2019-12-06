package com.app.rest.assign.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.app.rest.assign.model.TechnicalSkill;

public interface SkillRepository extends JpaRepository<TechnicalSkill, Integer>{
	
	@Query(value = "from TechnicalSkill t where t.employee.employeeId=:employeeId")
	public List<TechnicalSkill> getTechnicalSkillOfEmployee(Integer employeeId);

}
