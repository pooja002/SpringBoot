package com.employe.service;
import java.util.List;

import com.employe.dto.EmployeDTO;

public interface EmployeService {
	public void addEmploye(EmployeDTO Employe);
	public void removeEmploye(int empId);
	public EmployeDTO getEmploye(int empId);
	public List<EmployeDTO> getAllEmployees();
}
