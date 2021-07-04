package com.employe.service;
import java.util.List;

import com.employe.dto.EmployeDTO;
import com.employe.exceptions.NoSuchEmployeException;

public interface EmployeService {
	public void addEmploye(EmployeDTO Employe);
	public Integer removeEmploye(int empId) throws NoSuchEmployeException;
	public EmployeDTO getEmploye(int empId) throws NoSuchEmployeException;
	public List<EmployeDTO> getAllEmployees();
}
