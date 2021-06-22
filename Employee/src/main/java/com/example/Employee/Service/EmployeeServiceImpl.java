package com.example.Employee.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.example.Employee.dto.EmployeeDTO;
import com.example.Employee.repository.EmployeeRepository;


@Service("employeeService")
@Scope("prototype")
public class EmployeeServiceImpl implements EmployeeService {

// Autowiring the property
	
//	@Autowired
	private EmployeeRepository employeeRepository;
	
	
// Autowiring the setter method
	
//	@Autowired
//	public void setEmployeeRepository(EmployeeRepository employeeRepository) {
//		this.employeeRepository = employeeRepository;
//	}
//	
	
// Autowiring the constructor
	@Autowired
	EmployeeServiceImpl(EmployeeRepository employeeRepository)
	{
		this.employeeRepository = employeeRepository;
	}
	
	
	@Override
	public String createEmployee(EmployeeDTO employeeDTO) {
		employeeRepository.createEmployee(employeeDTO);
		return "Employee created with employeeId"+ employeeDTO.getEmployeeId();
	}

	

	@Override
	public List<EmployeeDTO> getAllEmployees() {
		
		return  employeeRepository.fetchEmployees();
	}

	@Override
	public void deleteEmployeee(int employeeId) {
		 employeeRepository.deleteEmployeee(employeeId);
	}
	
	

}
