package com.employe.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

import com.employe.dto.EmployeDTO;
import com.employe.entity.Employe;
import com.employe.exceptions.NoSuchEmployeException;
import com.employe.repository.EmployeRepository;
@PropertySource("classpath:ValidationMessages.properties")
@Transactional
@Service("employeService")
public class EmployeServiceImpl implements EmployeService{

	@Autowired
	private EmployeRepository repository;

	
	@Override
	public void addEmploye(EmployeDTO Employe) {
		repository.saveAndFlush(EmployeDTO.prepareEmployeEntity(Employe));

	}

	@Override
	public Integer removeEmploye(int empId) throws NoSuchEmployeException {
		Optional<Employe> optional = repository.findById(empId);
		Employe employeEntity = optional.orElseThrow(()->new NoSuchEmployeException("Employee "+empId+ " Does Not Exist"));
		if(!employeEntity.equals(null))
		{
		repository.deleteById(empId);
		}
		return empId;
	}
	
	@Override
	public EmployeDTO getEmploye(int empId) throws NoSuchEmployeException{
		Optional<Employe> optional = repository.findById(empId);
		Employe employeEntity = optional.orElseThrow(()->new NoSuchEmployeException("Employee "+empId+" Does Not Exist"));
		EmployeDTO employeDTO = employeEntity.prepareEmployeDTO(employeEntity);
		return employeDTO ;
	
	}
	
	@Override
	public List<EmployeDTO> getAllEmployees()
	{
		List<Employe> empRep = repository.findAll();
		List<EmployeDTO> empDtos = new ArrayList<>();
		for(Employe emp : empRep)
		{
			EmployeDTO e = new EmployeDTO();
			e.setEmpId(emp.getEmpId());
			e.setEmpName(emp.getEmpName());
			e.setBaseLocation(emp.getBaseLocation());
			e.setDepartment(emp.getDepartment());
			empDtos.add(e);
		}
		return empDtos;
		
	}

}
