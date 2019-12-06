package com.app.rest.assign.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.rest.assign.model.TechnicalSkill;
import com.app.rest.assign.repository.SkillRepository;

@Service
@Transactional
public class SkillServiceImpl implements SkillService {
	@Autowired
	private SkillRepository skillRepository;

	@Override
	public TechnicalSkill addTechnicalSkill(TechnicalSkill skill) {
		return skillRepository.save(skill);
	}

}
