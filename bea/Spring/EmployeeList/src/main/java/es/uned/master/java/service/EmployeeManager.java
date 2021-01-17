package es.uned.master.java.service;

import java.util.ArrayList;
import java.util.List;
import es.uned.master.java.model.Employee;

public class EmployeeManager {
	private static List<Employee> employeeList;

	
	public EmployeeManager(){
		this.employeeList = new ArrayList<Employee>();
		this.employeeList.add(new Employee("1", "Mike", "Smith"));
		this.employeeList.add(new Employee("2", "John", "Taylor"));
		this.employeeList.add(new Employee("3", "Dave", "Wilson"));
	}

	public List<Employee> getEmployeeList(){
		return employeeList;
	}

	public String toString(){
		return this.employeeList.toString();
	}
}