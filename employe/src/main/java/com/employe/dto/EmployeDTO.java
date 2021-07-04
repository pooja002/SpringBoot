package com.employe.dto;

import javax.validation.constraints.NotNull;

import com.employe.entity.Employe;

public class EmployeDTO {
	@NotNull(message="Please provide employee Id")
	private int empId;
	@NotNull(message="Please provide employee name")
    private String empName;
    private String department;
    private String baseLocation;
    
    public EmployeDTO(int empId, String empName, String department, String baseLocation) {
		super();
		this.empId = empId;
		this.empName = empName;
		this.department = department;
		this.baseLocation = baseLocation;
	}
	public EmployeDTO() {
		// TODO Auto-generated constructor stub
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
	
    
	@Override
	public String toString() {
		return "Employe [empId=" + empId + ", empName=" + empName + ", department=" + department + ", baseLocation="
				+ baseLocation + "]";
	}
	public static Employe prepareEmployeEntity(EmployeDTO employeDTO)
	{
		Employe employeEntity = new Employe();
		employeEntity.setEmpId(employeDTO.getEmpId());
		employeEntity.setEmpName(employeDTO.getEmpName());
		employeEntity.setBaseLocation(employeDTO.getBaseLocation());
		employeEntity.setDepartment(employeDTO.getDepartment());

		return employeEntity;
		
	}
    
   
}
