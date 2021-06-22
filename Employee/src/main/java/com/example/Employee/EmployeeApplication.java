package com.example.Employee;

import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.support.AbstractApplicationContext;

import com.example.Employee.Service.EmployeeServiceImpl;
import com.example.Employee.dto.EmployeeDTO;


@SpringBootApplication
public class EmployeeApplication {

	public static void main(String[] args) {
		EmployeeServiceImpl employeeService = null;
		AbstractApplicationContext context = (AbstractApplicationContext) SpringApplication
				.run(EmployeeApplication.class, args);
		employeeService = (EmployeeServiceImpl)context.getBean("employeeService");
		
		
		EmployeeServiceImpl service1 = (EmployeeServiceImpl) context.getBean("employeeService");
		
		System.out.println(employeeService==service1);
		
		EmployeeDTO empDTO = new EmployeeDTO();
		empDTO.setDepartment("Dunder Mifflin");
		empDTO.setEmployeeId(1224);
		empDTO.setEmployeeName("Jim");
		
		employeeService.createEmployee(empDTO);
		List<EmployeeDTO> employeeList = employeeService.getAllEmployees();
		for(EmployeeDTO employeeDTO : employeeList)
		{
			System.out.println(employeeDTO.getEmployeeName()+" "+employeeDTO.getEmployeeId()+" "+employeeDTO.getDepartment());
		}
		
		employeeService.deleteEmployeee(1224);
		System.out.println("After deletion");
		List<EmployeeDTO> employeeList1 = employeeService.getAllEmployees();
		for(EmployeeDTO employeeDTO : employeeList1)
		{
			System.out.println(employeeDTO.getEmployeeName()+" "+employeeDTO.getEmployeeId()+" "+employeeDTO.getDepartment());
		}
		
		
		
		
		context.close();
	}

}
