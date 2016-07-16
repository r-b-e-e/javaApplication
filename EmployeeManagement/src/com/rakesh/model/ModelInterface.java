package com.rakesh.model;

import java.util.*;

import org.json.JSONArray;

import com.rakesh.bean.Employee;

public interface ModelInterface {
	public abstract void insertEmployeeDetails(Employee employee);
	public abstract List<Employee> getAllEmployees();
	public abstract Employee getEmployeeData(int employeeID);
	public abstract void updateEmployee(Employee employeeToUpdate);
	public abstract JSONArray exportToJson(List<Employee> employeeList);
	public abstract void importToDatabase(JSONArray jsonArrayToDatabase);
}
