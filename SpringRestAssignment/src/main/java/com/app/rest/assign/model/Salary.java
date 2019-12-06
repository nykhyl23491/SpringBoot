package com.app.rest.assign.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "T_Salary")
public class Salary {
	private Integer salaryId;
	private double basicSalary;
	private double hra;
	private double da;
	private double salaryPerMonth;

	public Salary() {
		System.out.println("Salary.Salary()");
	}

	public Salary(double basicSalary, double hra, double da, double salaryPerMonth) {
		super();
		this.basicSalary = basicSalary;
		this.hra = hra;
		this.da = da;
		this.salaryPerMonth = salaryPerMonth;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "salary_id")
	public Integer getSalaryId() {
		return salaryId;
	}

	public void setSalaryId(Integer salaryId) {
		this.salaryId = salaryId;
	}

	@Column(name = "basic_salary", columnDefinition = "double(7,2)")
	public double getBasicSalary() {
		return basicSalary;
	}

	public void setBasicSalary(double basicSalary) {
		this.basicSalary = basicSalary;
	}

	@Column(name = "hra", columnDefinition = "double(7,2)")
	public double getHra() {
		return hra;
	}

	public void setHra(double hra) {
		this.hra = hra;
	}

	@Column(name = "da", columnDefinition = "double(7,2)")
	public double getDa() {
		return da;
	}

	public void setDa(double da) {
		this.da = da;
	}

	@Column(name = "salaryPerMonth", columnDefinition = "double(7,2)")
	public double getSalaryPerMonth() {
		return salaryPerMonth;
	}

	public void setSalaryPerMonth(double salaryPerMonth) {
		this.salaryPerMonth = salaryPerMonth;
	}

	@Override
	public String toString() {
		return "Salary [salaryId=" + salaryId + ", basicSalary=" + basicSalary + ", hra=" + hra + ", da=" + da
				+ ", salaryPerMonth=" + salaryPerMonth + "]";
	}

}
