package com.soapapplicationdemo_1.service;

import com.soapapplicationdemo_1.model.Employee;

public interface EmployeeService {
	
	Employee addEmployeeToDb(Employee employee);
	
	Employee getEmployeeById(int id);
	
	Employee updateEmployee(Employee employee);
	
	Boolean deleteEmployee(int id);

}
