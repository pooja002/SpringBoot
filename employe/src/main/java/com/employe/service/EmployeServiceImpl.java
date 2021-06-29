package com.employe.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.employe.dto.EmployeDTO;
import com.employe.entity.Employe;
import com.employe.repository.EmployeRepository;

@Service("employeService")
public class EmployeServiceImpl implements EmployeService{

	@Autowired
	private EmployeRepository repository;

	
	@Override
	public void addEmploye(EmployeDTO Employe) {
		repository.saveAndFlush(EmployeDTO.prepareEmployeEntity(Employe));

	}

	@Override
	public void removeEmploye(int empId) {
		repository.deleteById(empId);
		
	}

	@Override
	public EmployeDTO getEmploye(int empId) {
		Optional<Employe> optional = repository.findById(empId);
		Employe employeEntity = optional.get();// Converting Optional<Customer> to Customer
		EmployeDTO employeDTO = Employe.prepareEmployeDTO(employeEntity);
		return employeDTO ;
	
	}
	
	@Override
	public List<EmployeDTO> getAllEmployees()
	{
		List<Employe> empRep = repository.findAll();
		List<EmployeDTO> empDto = new ArrayList<>();
		for(Employe emp : empRep)
		{
			EmployeDTO e = new EmployeDTO();
			e.setEmpId(emp.getEmpId());
			e.setEmpName(emp.getEmpName());
			e.setBaseLocation(emp.getBaseLocation());
			e.setDepartment(emp.getDepartment());
			empDto.add(e);
		}
		return empDto;
		
	}

}
