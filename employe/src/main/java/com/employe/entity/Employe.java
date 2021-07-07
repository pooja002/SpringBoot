package com.employe.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;


import com.employe.dto.EmployeDTO;

@Entity
public class Employe {
	@Id
	@Column(name="emp_id")
	private int empId;
	@Column(name="emp_name")
    private String empName;
    private String department;
    private String baseLocation;
    
    
    public Employe()
    {
    	
    }
    
	public Employe(int empId, String empName, String department, String baseLocation) {
		super();
		this.empId = empId;
		this.empName = empName;
		this.department = department;
		this.baseLocation = baseLocation;
	}
	public int getEmpId() {
		return empId;
	}
	public void setEmpId(int empId) {
		this.empId = empId;
	}
	public String getEmpName() {
		return empName;
	}
	public void setEmpName(String empName) {
		this.empName = empName;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	public String getBaseLocation() {
		return baseLocation;
	}
	public void setBaseLocation(String baseLocation) {
		this.baseLocation = baseLocation;
	}
    
	public static EmployeDTO prepareEmployeDTO(Employe employe)
	{
		EmployeDTO employeDTO = new EmployeDTO();
		employeDTO.setEmpId(employe.getEmpId());
		employeDTO.setEmpName(employe.getEmpName());
		employeDTO.setDepartment(employe.getDepartment());
		employeDTO.setBaseLocation(employe.getBaseLocation());
		return employeDTO;
		
	}

	@Override
	public String toString() {
		return "Employe [empId=" + empId + ", empName=" + empName + ", department=" + department + ", baseLocation="
				+ baseLocation + "]";
	}
	
	
}
