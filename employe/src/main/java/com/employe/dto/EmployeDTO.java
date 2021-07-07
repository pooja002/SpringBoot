package com.employe.dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.validation.annotation.Validated;

import com.employe.entity.Employe;
@Validated
public class EmployeDTO {
    @NotNull(message="{EmpId.absent}")
    @Min(value = 1000, message = "The value must be greater than 1000")
    private int empId;
    @NotEmpty(message="{empName.absent}")
    private String empName;
    @NotEmpty(message="Please provide department")
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
