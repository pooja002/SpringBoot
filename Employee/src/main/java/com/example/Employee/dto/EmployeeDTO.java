package com.example.Employee.dto;

public class EmployeeDTO {
		
	 int employeeId;
	 String employeeName;
	 String department;

	public int getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}
	public String getEmployeeName() {
		return employeeName;
	}
	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	 
	public EmployeeDTO(int employeeId, String employeeName, String department) {
	super();
		this.employeeId = employeeId;
		this.employeeName = employeeName;
		this.department = department;
	}
	public EmployeeDTO()
	{
		
	}
	 
}
