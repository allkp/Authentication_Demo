package com.soapapplicationdemo_1.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.soapapplicationdemo_1.model.Employee;
import com.soapapplicationdemo_1.repo.EmployeeRepo;


@Service
public class EmployeeServiceImpl implements EmployeeService{
	
	@Autowired
	private EmployeeRepo repo;

	@Override
	public Employee addEmployeeToDb(Employee employee) {
		return repo.save(employee);
		
	}

	@Override
	public Employee getEmployeeById(int id) {
		Employee emp = repo.findById(id).orElse(null);
		return emp;
	}

	@Override
	public Employee updateEmployee(Employee employee) {
		if(repo.existsById(employee.getId())) {
			return repo.save(employee);
		}
		return null;
		
	}

	@Override
	public Boolean deleteEmployee(int id) {
		if(repo.existsById(id)) {
			repo.deleteById(id);
			return true;
		}
		return false;
	}

}
