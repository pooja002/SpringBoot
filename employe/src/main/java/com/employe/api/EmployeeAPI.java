package com.employe.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.validation.Valid;
import com.employe.dto.EmployeDTO;
import com.employe.service.EmployeService;

@RestController
@RequestMapping("/employees")
public class EmployeeAPI {

	@Autowired
	EmployeService service;
	
	@GetMapping("/getEmployee")
	public List<EmployeDTO> getEmploye()
	{
		return service.getAllEmployees();
	}
	
	@PostMapping("/")
	public ResponseEntity<String> addEmployee( @Valid @RequestBody EmployeDTO employeDTO)
	{
		service.addEmploye(employeDTO);
		return new ResponseEntity<>("New row Created",HttpStatus.CREATED);
		
	}
}
