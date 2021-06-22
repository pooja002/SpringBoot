package com.example.Employee.repository;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import com.example.Employee.dto.EmployeeDTO;



@Repository
@Scope("singleton")
public class EmployeeRepository {
List<EmployeeDTO> employees = null;
	
	@PostConstruct
	public void initializer()
	{
		EmployeeDTO employeeDTO = new EmployeeDTO();
		employeeDTO.setEmployeeId(1223);
		employeeDTO.setEmployeeName("Micheal Scott");
		employeeDTO.setDepartment("Dunder Mifflin");
		employees = new ArrayList<>();
		employees.add(employeeDTO);
	}
	
	public void createEmployee(EmployeeDTO employeeDTO)
	{
		employees.add(employeeDTO);
	}
	
	public List<EmployeeDTO> fetchEmployees()
	{
		return employees;
	}
	
	public void deleteEmployeee(int employeeId)
	{
		
			for(EmployeeDTO employeeDTO:employees)
			{
				if(employeeDTO.getEmployeeId()==(employeeId))
				{
					employees.remove(employeeDTO);
					break;
				}
			}		
			
			
	}
}
