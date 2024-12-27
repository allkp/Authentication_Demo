package com.soapapplicationdemo_1.endpoint;

import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import com.soapapplicationdemo_1.apis_pack.AddEmployeeRequest;
import com.soapapplicationdemo_1.apis_pack.AddEmployeeResponse;
import com.soapapplicationdemo_1.apis_pack.DeleteEmployeeRequest;
import com.soapapplicationdemo_1.apis_pack.DeleteEmployeeResponse;
import com.soapapplicationdemo_1.apis_pack.EmployeeInfo;
import com.soapapplicationdemo_1.apis_pack.GetEmployeeByIdRequest;
import com.soapapplicationdemo_1.apis_pack.GetEmployeeResponse;
import com.soapapplicationdemo_1.apis_pack.ServiceStatus;
import com.soapapplicationdemo_1.apis_pack.UpdateEmployeeRequest;
import com.soapapplicationdemo_1.apis_pack.UpdateEmployeeResponse;
import com.soapapplicationdemo_1.model.Employee;
import com.soapapplicationdemo_1.service.EmployeeService;



@Endpoint
public class EmployeeEndpoint {
	
	private static final String NAMESPACE_URI = "http://apis_pack.soapapplicationdemo_1.com";	
	
	private EmployeeService empService;
	
	// Constructor injection for empService
    public EmployeeEndpoint(EmployeeService empService) {
        this.empService = empService;
    }
	
	// Add employee
	@PayloadRoot(namespace=NAMESPACE_URI, localPart = "addEmployeeRequest")
	@ResponsePayload
	public AddEmployeeResponse addEmployee(@RequestPayload AddEmployeeRequest request) {
		
		EmployeeInfo empInfo = request.getEmployeeInfo();
		
		Employee emp = new Employee();
		
		emp.setId(empInfo.getId());
		emp.setName(empInfo.getName());
		emp.setDepartment(empInfo.getDepartment());
		emp.setContact(empInfo.getContact());
		emp.setAddress(empInfo.getAddress());
		
		Employee savedEmployee = empService.addEmployeeToDb(emp);
		
		AddEmployeeResponse response = new AddEmployeeResponse();
		ServiceStatus serviceStatus = new ServiceStatus();
		
		serviceStatus.setStatus("Success......!!!");
		serviceStatus.setMessage("Employee Entity Successfully added....!!!");
		response.setServiceStatus(serviceStatus);
		
		EmployeeInfo savedEmployeeInfo = new EmployeeInfo();
		savedEmployeeInfo.setId(savedEmployee.getId());
		savedEmployeeInfo.setName(savedEmployee.getName());
		savedEmployeeInfo.setDepartment(savedEmployee.getDepartment());
		savedEmployeeInfo.setContact(savedEmployee.getContact());
		savedEmployeeInfo.setAddress(savedEmployee.getAddress());
		
		response.setEmployeeInfo(savedEmployeeInfo);
		
		return response;
	}
	
	
	//Get Employee By Id
	@PayloadRoot(namespace=NAMESPACE_URI, localPart="getEmployeeByIdRequest")
	@ResponsePayload
	public GetEmployeeResponse getEmployeeResponse(@RequestPayload GetEmployeeByIdRequest request) {
		int id = request.getId();
		
		Employee emp = empService.getEmployeeById(id);
		
		GetEmployeeResponse response = new GetEmployeeResponse();
		
		if(emp != null) {
			EmployeeInfo empInfo = new EmployeeInfo();
			empInfo.setId(emp.getId());
			empInfo.setName(emp.getName());
			empInfo.setDepartment(emp.getDepartment());
			empInfo.setContact(emp.getContact());
			empInfo.setAddress(emp.getAddress());
			
			response.setEmployeeInfo(empInfo);
		}
		
		return response;
		
	}
	
	//Update Employee
	@PayloadRoot(namespace=NAMESPACE_URI, localPart="updateEmployeeRequest")
	@ResponsePayload
	public UpdateEmployeeResponse updateEmployeeResponse(@RequestPayload UpdateEmployeeRequest request) {
		
		EmployeeInfo empInfo = request.getEmployeeInfo();
		
		Employee emp = new Employee();
		
		emp.setId(empInfo.getId());
		emp.setName(empInfo.getName());
		emp.setDepartment(empInfo.getDepartment());
		emp.setContact(empInfo.getContact());
		emp.setAddress(empInfo.getAddress());
		
		Employee updatedEmp = empService.updateEmployee(emp);
		
		UpdateEmployeeResponse response = new UpdateEmployeeResponse();
		ServiceStatus serviceStatus = new ServiceStatus();
		
		if(updatedEmp != null) {
			serviceStatus.setStatus("Success...!!!");
			serviceStatus.setMessage("Employee Updated Successfully.....!!!");
		}else {
			serviceStatus.setStatus("Failed....!!!");
			serviceStatus.setMessage("Employee Update Failed....!!!");
		}
		
		response.setServiceStatus(serviceStatus);
		
		return response;
	}
	
	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "deleteEmployeeRequest")
	@ResponsePayload
	public DeleteEmployeeResponse deleteEmployeeResponse(@RequestPayload DeleteEmployeeRequest request) {
		int id = request.getId();
		
		boolean isDeleted = empService.deleteEmployee(id);
		
		DeleteEmployeeResponse response = new DeleteEmployeeResponse();
		ServiceStatus serviceStatus = new ServiceStatus();
		
		if(isDeleted) {
			serviceStatus.setStatus("Success...!!!");
			serviceStatus.setMessage("Employee Deleted Successfully....!!!");
		}else {
			serviceStatus.setStatus("Failed.....!!!");
			serviceStatus.setMessage("Employee not found....!!!");
		}
		
		response.setServiceStatus(serviceStatus);
		
		return response;
		
	}

}
