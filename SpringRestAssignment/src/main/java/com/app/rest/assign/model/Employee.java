package com.app.rest.assign.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import com.fasterxml.jackson.annotation.JsonBackReference;

import io.swagger.annotations.ApiModelProperty;

@Entity
@Table(name = "T_Employee")
@XmlRootElement
@XmlAccessorType(value = XmlAccessType.FIELD)
//@XmlAccessorType(value = XmlAccessType.NONE)
public class Employee implements Serializable {

	private static final long serialVersionUID = 99L;

	@ApiModelProperty(notes = "Employee ID", name = "employeeId", required = true, value = "1001")
	private Integer employeeId;
	@ApiModelProperty(notes = "Employee Name", name = "employeeName", required = true, value = "Name")
	private String employeeName;
	@ApiModelProperty(notes = "Employee Email", name = "employeeEmail", required = true, value = "Email")
	private String employeeEmail;
	@ApiModelProperty(notes = "Employee Designation", name = "employeeDesignation", required = true, value = "Designation")
	private String employeeDesignation;
	@ApiModelProperty(notes = "Employee ContactNumber", name = "employeeContactNumber", required = true, value = "111111111")
	private String employeeContactNumber;
	@ApiModelProperty(notes = "Employee Location", name = "employeeLocation", required = true, value = "Location")
	private String employeeLocation;
	@ApiModelProperty(notes = "Employee Department", name = "department", required = true)
	@JsonBackReference
	@XmlTransient
	private Department department;

	private Salary salary;

	private List<TechnicalSkill> skills;

	public Employee() {
		System.out.println("Constructor Employee.Employee()");
	}

	public Employee(Integer employeeId, String employeeName, String employeeEmail, String employeeDesignation,
			String employeeContactNumber, String employeeLocation, Department department) {
		super();
		this.employeeId = employeeId;
		this.employeeName = employeeName;
		this.employeeEmail = employeeEmail;
		this.employeeDesignation = employeeDesignation;
		this.employeeContactNumber = employeeContactNumber;
		this.employeeLocation = employeeLocation;
		this.department = department;
	}

	public Employee(Integer employeeId, String employeeName, String employeeEmail, String employeeDesignation,
			String employeeContactNumber, String employeeLocation) {
		super();
		this.employeeId = employeeId;
		this.employeeName = employeeName;
		this.employeeEmail = employeeEmail;
		this.employeeDesignation = employeeDesignation;
		this.employeeContactNumber = employeeContactNumber;
		this.employeeLocation = employeeLocation;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "Employee_Id")
	public Integer getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(Integer employeeeId) {
		this.employeeId = employeeeId;
	}

	@Column(name = "Employee_Name")
	public String getEmployeeName() {
		return employeeName;
	}

	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}

	@Column(name = "Employee_Email", unique = true)
	public String getEmployeeEmail() {
		return employeeEmail;
	}

	public void setEmployeeEmail(String employeeEmail) {
		this.employeeEmail = employeeEmail;
	}

	@Column(name = "Employee_Designation")
	public String getEmployeeDesignation() {
		return employeeDesignation;
	}

	public void setEmployeeDesignation(String employeeDesignation) {
		this.employeeDesignation = employeeDesignation;
	}

	@Column(name = "Employee_Contact", unique = true)
	public String getEmployeeContactNumber() {
		return employeeContactNumber;
	}

	public void setEmployeeContactNumber(String employeeContactNumber) {
		this.employeeContactNumber = employeeContactNumber;
	}

	@Column(name = "Employee_Location")
	public String getEmployeeLocation() {
		return employeeLocation;
	}

	public void setEmployeeLocation(String employeeLocation) {
		this.employeeLocation = employeeLocation;
	}

	@ManyToOne
	@JoinColumn(name = "department_id")
	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "salary_id")
	public Salary getSalary() {
		return salary;
	}

	public void setSalary(Salary salary) {
		this.salary = salary;
	}

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "employee")
	public List<TechnicalSkill> getSkills() {
		return skills;
	}

	public void setSkills(List<TechnicalSkill> skills) {
		this.skills = skills;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((employeeContactNumber == null) ? 0 : employeeContactNumber.hashCode());
		result = prime * result + ((employeeDesignation == null) ? 0 : employeeDesignation.hashCode());
		result = prime * result + ((employeeEmail == null) ? 0 : employeeEmail.hashCode());
		result = prime * result + ((employeeId == null) ? 0 : employeeId.hashCode());
		result = prime * result + ((employeeLocation == null) ? 0 : employeeLocation.hashCode());
		result = prime * result + ((employeeName == null) ? 0 : employeeName.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Employee other = (Employee) obj;
		if (employeeContactNumber == null) {
			if (other.employeeContactNumber != null)
				return false;
		} else if (!employeeContactNumber.equals(other.employeeContactNumber))
			return false;
		if (employeeDesignation == null) {
			if (other.employeeDesignation != null)
				return false;
		} else if (!employeeDesignation.equals(other.employeeDesignation))
			return false;
		if (employeeEmail == null) {
			if (other.employeeEmail != null)
				return false;
		} else if (!employeeEmail.equals(other.employeeEmail))
			return false;
		if (employeeId == null) {
			if (other.employeeId != null)
				return false;
		} else if (!employeeId.equals(other.employeeId))
			return false;
		if (employeeLocation == null) {
			if (other.employeeLocation != null)
				return false;
		} else if (!employeeLocation.equals(other.employeeLocation))
			return false;
		if (employeeName == null) {
			if (other.employeeName != null)
				return false;
		} else if (!employeeName.equals(other.employeeName))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Employee [employeeeId=" + employeeId + ", employeeName=" + employeeName + ", employeeEmail="
				+ employeeEmail + ", employeeDesignation=" + employeeDesignation + ", employeeContactNumber="
				+ employeeContactNumber + ", employeeLocation=" + employeeLocation + "]";
	}

}
