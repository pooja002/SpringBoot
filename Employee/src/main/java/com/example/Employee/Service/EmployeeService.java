package com.example.Employee.Service;

import java.util.List;

import com.example.Employee.dto.EmployeeDTO;

public interface EmployeeService {
	public String createEmployee(EmployeeDTO employeeDTO);
	public void deleteEmployeee(int employeeId);
	public List<EmployeeDTO> getAllEmployees();
	
}
