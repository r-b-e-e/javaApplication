package com.rakesh.view;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.*;

import org.json.JSONArray;
import org.json.JSONException;

import com.rakesh.bean.Employee;
import com.rakesh.util.ScannerFactory;

public class View implements ViewInterface {

	@Override
	public Employee getEmployeeDetails() {
		Employee employee = new Employee();

		employee.setEmployee_id(0);
		System.out.print("\nEnter First Name (without space): ");
		employee.setFirst_name(ScannerFactory.getScannerObject().next());
		System.out.print("\nEnter Last Name (without space): ");
		employee.setLast_name(ScannerFactory.getScannerObject().next());
		System.out.print("\nEnter Employment Start Date (mm-dd-yyyy): ");
		employee.setEmployment_start_date(ScannerFactory.getScannerObject().next());
		System.out.print("\nEnter Employee Type (Full-Time/Part-Time/Intern/Contract): ");
		employee.setEmployee_type(ScannerFactory.getScannerObject().next());
		System.out.print("\nEnter Salary Based (per-hour/per-annum): ");
		employee.setSalary_based(ScannerFactory.getScannerObject().next());
		System.out.print("\nEnter Salary (only numbers): ");
		employee.setSalary(ScannerFactory.getScannerObject().nextInt());

		System.out.println();
		return employee;
	}

	@Override
	public void displayEmployees(List<Employee> listEmployees) {
		System.out.println("\n************************************************************");
		System.out.printf("%12s   %-15s  %-15s %-12s  %-15s   %-10s %-10s%n", "Employee Id", "First Name", "Last Name",
				"Start Date", "Employee Type", "Salary", "Salary Based");

		ListIterator<Employee> itr = listEmployees.listIterator();
		while (itr.hasNext()) {
			Employee singleEmployee = itr.next();
			System.out.printf("%12d   %-15s  %-15s %-12s  %-15s   %-10d %-10s%n", singleEmployee.getEmployee_id(),
					singleEmployee.getFirst_name(), singleEmployee.getLast_name(),
					singleEmployee.getEmployment_start_date(), singleEmployee.getEmployee_type(),
					singleEmployee.getSalary(), singleEmployee.getSalary_based());
		}
		System.out.println("************************************************************\n");
	}

	@Override
	public void display(String message) {
		System.out.println("\n********************************");
		System.out.println(message);
		System.out.println("********************************\n");
	}

	@Override
	public int getEmployeeIdToUpdate() {
		System.out.println("Enter the Employee ID to update: ");
		return ScannerFactory.getScannerObject().nextInt();
	}

	@Override
	public Employee displayEmployeeData(Employee singleEmployee) {
		System.out.println("\n************************************************************");
		System.out.printf("%12s   %-15s  %-15s %-12s  %-15s   %-10s %-10s%n", "Employee Id", "First Name", "Last Name",
				"Start Date", "Employee Type", "Salary", "Salary Based");

		System.out.printf("%12d   %-15s  %-15s %-12s  %-15s   %-10d %-10s%n", singleEmployee.getEmployee_id(),
				singleEmployee.getFirst_name(), singleEmployee.getLast_name(),
				singleEmployee.getEmployment_start_date(), singleEmployee.getEmployee_type(),
				singleEmployee.getSalary(), singleEmployee.getSalary_based());

		System.out.println("************************************************************\n");

		System.out.println("Enter the field to update: ");
		System.out.println("1. First Name");
		System.out.println("2. Last Name");
		System.out.println("3. Employment Start Date (mm-dd-yyyy)");
		System.out.println("4. Employee Type (Full-Time/Part-Time/Intern/Contract)");
		System.out.println("5. Salary Based (per-hour/per-annum)");
		System.out.println("6. Enter Salary");

		switch (ScannerFactory.getScannerObject().nextInt()) {
		case 1:
			System.out.print("\nEnter First Name (without space): ");
			singleEmployee.setFirst_name(ScannerFactory.getScannerObject().next());
			break;
		case 2:
			System.out.print("\nEnter Last Name (without space): ");
			singleEmployee.setLast_name(ScannerFactory.getScannerObject().next());
			break;
		case 3:
			System.out.print("\nEnter Employment Start Date (mm-dd-yyyy): ");
			singleEmployee.setEmployment_start_date(ScannerFactory.getScannerObject().next());
			break;
		case 4:
			System.out.print("\nEnter Employee Type (Full-Time/Part-Time/Intern/Contract): ");
			singleEmployee.setEmployee_type(ScannerFactory.getScannerObject().next());
			break;
		case 5:
			System.out.print("\nEnter Salary Based (per-hour/per-annum): ");
			singleEmployee.setSalary_based(ScannerFactory.getScannerObject().next());
			break;
		case 6:
			System.out.print("\nEnter Salary (only numbers): ");
			singleEmployee.setSalary(ScannerFactory.getScannerObject().nextInt());
			break;
		}

		return singleEmployee;
	}

	@Override
	public void exportToJsonFile(JSONArray jsonArray) {
		PrintWriter writer = null;
		try {
			writer = new PrintWriter("employee.json", "UTF-8");
			writer.println(jsonArray.toString());
		} catch (FileNotFoundException | UnsupportedEncodingException e) {
			e.printStackTrace();
		} finally {
			writer.close();
		}

	}

	@Override
	public JSONArray importFromJsonFile() {
		BufferedReader br = null;
		StringBuffer sb = new StringBuffer();
		try {
			String sCurrentLine;
			br = new BufferedReader(new FileReader("employee.json"));
			while ((sCurrentLine = br.readLine()) != null) {
				sb.append(sCurrentLine);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				br.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		JSONArray jsonArray = null;
		try {
			jsonArray = new JSONArray(sb.toString());
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return jsonArray;
	}
}
