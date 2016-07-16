package com.rakesh.controller;

import java.util.*;

import com.rakesh.bean.Employee;
import com.rakesh.model.ModelDao;
import com.rakesh.model.ModelInterface;
import com.rakesh.util.ScannerFactory;
import com.rakesh.view.View;
import com.rakesh.view.ViewInterface;

public class Controller {
	private ModelInterface modelInterface;
	private ViewInterface viewInterface;

	Controller(ModelInterface modelInterface, ViewInterface viewInterface) {
		this.modelInterface = modelInterface;
		this.viewInterface = viewInterface;

	}

	public void employeeOperation() {
		while (true) {
			System.out.println("EMPLOYEE MANAGEMENT");
			System.out.println("1. ADD NEW EMPLOYEE");
			System.out.println("2. DISPLAY ALL EMPLOYEES");
			System.out.println("3. EDIT EMPLOYEE");
			System.out.println("4  Export Employee JSON");
			System.out.println("5. Import Employee JSON");
			System.out.println("0. EXIT");

			switch (ScannerFactory.getScannerObject().nextInt()) {
			case 1:
				this.addEmployee();
				break;
			case 2:
				this.showEmployee();
				break;
			case 3:
				this.editEmployee();
				break;
			case 4:
				this.exportEmployee();
				break;
			case 5:
				this.importEmployee();
				break;
			case 0:
				System.exit(0);
				break;
			}
		}
	}
	
	public void importEmployee(){
		modelInterface.importToDatabase(viewInterface.importFromJsonFile());
		viewInterface.display("Employees in JSON file imported to the Database.");
	}
	
	public void exportEmployee(){
		viewInterface.exportToJsonFile(modelInterface.exportToJson(modelInterface.getAllEmployees()));
		viewInterface.display("Employees Exported as JSON. Please check the project folder.");
	}
	
	public void addEmployee() {
		modelInterface.insertEmployeeDetails(viewInterface.getEmployeeDetails());
		viewInterface.display("Employee Added");
	}

	public void showEmployee() {
		viewInterface.displayEmployees(modelInterface.getAllEmployees());
	}

	public void editEmployee() {
		viewInterface.displayEmployees(modelInterface.getAllEmployees());
		Employee singleEmployee = modelInterface.getEmployeeData(viewInterface.getEmployeeIdToUpdate());
		modelInterface.updateEmployee(viewInterface.displayEmployeeData(singleEmployee));
		viewInterface.display("Employee Updated");
	}

	public static void main(String[] args) {
		ModelInterface modelInterface = new ModelDao();
		ViewInterface viewInterface = new View();

		Controller controller = new Controller(modelInterface, viewInterface);
		controller.employeeOperation();
	}

}
