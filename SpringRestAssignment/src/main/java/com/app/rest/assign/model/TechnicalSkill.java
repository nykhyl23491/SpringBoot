package com.app.rest.assign.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "T_Skill")
public class TechnicalSkill {

	private Integer skillId;
	private String technology;
	private String type;
	private int experience;
	@JsonBackReference
	private Employee employee;

	public TechnicalSkill() {
		System.out.println("TechnicalSkill.TechnicalSkill()");
	}

	public TechnicalSkill(String technology, String type, int experience) {
		super();
		this.technology = technology;
		this.type = type;
		this.experience = experience;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "skill_id")
	public Integer getSkillId() {
		return skillId;
	}

	public void setSkillId(Integer skillId) {
		this.skillId = skillId;
	}

	@Column(name = "technology")
	public String getTechnology() {
		return technology;
	}

	public void setTechnology(String technology) {
		this.technology = technology;
	}

	@Column(name = "type")
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Column(name = "experience")
	public int getExperience() {
		return experience;
	}

	public void setExperience(int experience) {
		this.experience = experience;
	}

	@ManyToOne
	@JoinColumn(name = "employee_id")
	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	@Override
	public String toString() {
		return "TechnicalSkill [skillId=" + skillId + ", technology=" + technology + ", type=" + type + ", experience="
				+ experience + "]";
	}

}
