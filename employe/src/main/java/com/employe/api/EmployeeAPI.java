package com.employe.api;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.employe.dto.EmployeDTO;
import com.employe.exceptions.NoSuchEmployeException;
import com.employe.service.EmployeService;

@CrossOrigin
@RestController
@RequestMapping("/employees")
public class EmployeeAPI {

	@Autowired
	EmployeService service;
	
	@GetMapping("/getEmployees")
	public List<EmployeDTO> getEmploye()
	{
		return service.getAllEmployees();
	}
	
	//Handling URI using PathVariable
	@GetMapping(value="/getEmploye/{empId}")
	public EmployeDTO getByEmployeId(@PathVariable("empId") int empId) throws NoSuchEmployeException
	{
		return service.getEmploye(empId);
	}
	
	//Handling URI using RequestParam
	@GetMapping(value="/getEmployeRP")
	public EmployeDTO getByEmployeIdByRequestParam(@RequestParam int empId) throws NoSuchEmployeException
	{
		return service.getEmploye(empId);
	}
	
	@PostMapping("/")
	public ResponseEntity addEmployee( @Valid @RequestBody EmployeDTO employeDTO)
	{
		
		service.addEmploye(employeDTO);
		return new ResponseEntity<>("New row Created",HttpStatus.CREATED);	
	}
	
	//Handling URI using PathVariable
	@DeleteMapping(value="/{empId}")
	public ResponseEntity<String>  deleteEmployee(@PathVariable("empId") int empId) throws NoSuchEmployeException
	{
		service.removeEmploye(empId);
		return new ResponseEntity<>("Employee with deleted sucessfully", HttpStatus.OK);
	}
	
	
	//Handling URI using RequestParam
	@DeleteMapping(value="delete")
	public ResponseEntity<String>  deleteEmploye(@RequestParam("empId") int empId) throws NoSuchEmployeException
	{
		service.removeEmploye(empId);
		return new ResponseEntity<>("Employee with deleted sucessfully", HttpStatus.OK);
	}
	
}
