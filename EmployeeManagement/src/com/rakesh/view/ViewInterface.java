package com.rakesh.view;

import java.util.List;

import org.json.JSONArray;

import com.rakesh.bean.Employee;

public interface ViewInterface {
	public abstract Employee getEmployeeDetails();
	public abstract void display(String message);
	public abstract void displayEmployees(List<Employee> listEmployees);
	public abstract int getEmployeeIdToUpdate();
	public abstract Employee displayEmployeeData(Employee employeeToUpdate);
	public abstract void exportToJsonFile(JSONArray jsonArray);
	public abstract JSONArray importFromJsonFile();
}
