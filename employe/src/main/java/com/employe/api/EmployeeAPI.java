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

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@CrossOrigin
@RestController
@RequestMapping("/employees")
public class EmployeeAPI {

	@Autowired
	EmployeService service;
	
	@GetMapping(value="/getEmployees",produces="application/json")
	@ApiOperation(value="Fetch all the employees of Dunder Mifflin",response=EmployeDTO.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Fetched the customers successfully"),
			@ApiResponse(code = 404, message = "Customer details not found") })
	
	public List<EmployeDTO> getEmploye()
	{
		return service.getAllEmployees();
	}
	
	//Handling URI using PathVariable and Versioning
	@GetMapping(value="/getEmploye/{empId}") 
	public ResponseEntity<EmployeDTO> getByEmployeId(@PathVariable("empId") int empId) throws NoSuchEmployeException
	{
		EmployeDTO emp = new EmployeDTO();
		if(service.getEmploye(empId).equals("Employee "+empId+" Does Not Exist"))
		{
			return new ResponseEntity<>(emp,HttpStatus.OK);
		}
		else
		{
		return  new ResponseEntity<>(service.getEmploye(empId),HttpStatus.BAD_REQUEST);
		}
	}
	
//	@GetMapping(value="/getEmploye/{empId}",params="version=2")
//	public String getByEmployeIdv2(@PathVariable("empId") int empId) throws NoSuchEmployeException
//	{
//		EmployeDTO e = service.getEmploye(empId);
//		return e.getEmpId()+" "+" "+e.getEmpName()+e.getDepartment();
//	}
	
	
	//Handling URI using RequestParam
	@GetMapping(value="/getEmployeRP")
	public EmployeDTO getByEmployeIdByRequestParam(@RequestParam int empId) throws NoSuchEmployeException
	{
		return service.getEmploye(empId);
	}
	
	@PostMapping("/")
	public ResponseEntity addEmployee( @Valid @RequestBody EmployeDTO employeDTO) throws NoSuchEmployeException
	{
		
		service.addEmploye(employeDTO);
		return new ResponseEntity<>("New row Created with employee Id "+employeDTO.getEmpId(),HttpStatus.CREATED);	
	}
	
	//Handling URI using PathVariable
	@DeleteMapping(value="/{empId}")
	public ResponseEntity<String>  deleteEmployee(@PathVariable("empId") int empId) throws NoSuchEmployeException
	{
		if(service.removeEmploye(empId).equals("Employee "+empId+" Does Not Exist"))
		{
			return new ResponseEntity<>("Employee "+empId+" Does Not Exist", HttpStatus.OK);
		}
		else
		{
		return new ResponseEntity<>("Employee with "+empId+" deleted sucessfully", HttpStatus.OK);
		}
	}
	
	
	//Handling URI using RequestParam
	@DeleteMapping(value="delete")
	public ResponseEntity<String>  deleteEmploye(@RequestParam("empId") int empId) throws NoSuchEmployeException
	{
		service.removeEmploye(empId);
		return new ResponseEntity<>("Employee with deleted sucessfully", HttpStatus.OK);
	}
	
}
