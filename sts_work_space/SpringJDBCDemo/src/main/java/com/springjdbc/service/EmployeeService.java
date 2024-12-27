package com.springjdbc.service;


import org.springframework.stereotype.Service;

import com.springjdbc.model.Employee;
import com.springjdbc.repo.EmployeeRepo;

import java.util.List;

@Service
public class EmployeeService {

    private final EmployeeRepo employeeRepository;

    public EmployeeService(EmployeeRepo employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public Employee addEmployee(Employee employee) {
        employeeRepository.save(employee);
        return employee;
    }

    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    public Employee getEmployeeById(Long id) {
        return employeeRepository.findById(id);
    }

    public Employee updateEmployee(Employee employee) {
        employeeRepository.update(employee);
        return employee;
    }

    public void deleteEmployee(Long id) {
        employeeRepository.delete(id);
    }
}

