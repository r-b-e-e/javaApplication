package com.rakesh.bean;

import java.io.Serializable;
import javax.persistence.*;

@Entity(name = "Employee")
public class Employee {

	@Id
	@Column(name = "employee_id")
	private int employee_id;
	@Column(name = "first_name")
	private String first_name;
	@Column(name = "last_name")
	private String last_name;
	@Column(name = "employment_start_date")
	private String employment_start_date;
	@Column(name = "employee_type")
	private String employee_type;
	@Column(name = "salary")
	private int salary;
	@Column(name = "salary_based")
	private String salary_based;

	public int getEmployee_id() {
		return employee_id;
	}

	public void setEmployee_id(int employee_id) {
		this.employee_id = employee_id;
	}

	public String getFirst_name() {
		return first_name;
	}

	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}

	public String getLast_name() {
		return last_name;
	}

	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}

	public String getEmployment_start_date() {
		return employment_start_date;
	}

	public void setEmployment_start_date(String employment_start_date) {
		this.employment_start_date = employment_start_date;
	}

	public String getEmployee_type() {
		return employee_type;
	}

	public void setEmployee_type(String employee_type) {
		this.employee_type = employee_type;
	}

	public int getSalary() {
		return salary;
	}

	public void setSalary(int salary) {
		this.salary = salary;
	}

	public String getSalary_based() {
		return salary_based;
	}

	public void setSalary_based(String salary_based) {
		this.salary_based = salary_based;
	}

}
