package com.employe;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.employe.api.EmployeeAPI;
import com.employe.dto.EmployeDTO;
import com.employe.service.EmployeService;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.Assert;

@RunWith(SpringRunner.class)
@WebMvcTest(value=EmployeeAPI.class)
public class EmployeControllerTests {

	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private EmployeService employeeService;
	
	@Test
	public void getAllEmployeesTest() throws Exception
	{
		List<EmployeDTO> employeeDTOs = new ArrayList<>();
		EmployeDTO emp = null;
		employeeDTOs.add(new EmployeDTO(1001,"Jim","Dunder Mifflin","Scranton"));
		employeeDTOs.add(new EmployeDTO(1002,"Pamela","Dunder Mifflin Paper Company","Scranton"));
		employeeDTOs.add(new EmployeDTO(1005,"Ryan","Dunder Mifflin Paper Company","Scranton"));
		employeeDTOs.add(new EmployeDTO(1006,"Dwight Schrutte","Dunder Mifflin Paper Company","Scranton"));
		employeeDTOs.add(new EmployeDTO(1007,"Toby","Dunder Mifflin Paper Company","Scranton"));
		
		Mockito.when(employeeService.getAllEmployees()).thenReturn(employeeDTOs);
		
		RequestBuilder reqBuilder =  MockMvcRequestBuilders.get("/employees/getEmployees").accept(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(reqBuilder).andReturn();
		MockHttpServletResponse response = result.getResponse();
		
		//Serialize employeeDTO to json format
		ObjectMapper mapper = new ObjectMapper();
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		mapper.writeValue(out, employeeDTOs);
		byte[] data = out.toByteArray();
		Assert.assertEquals(new String(data), response.getContentAsString());
	}
	
	@Test
	public void addEmployeeValidTest() throws Exception
	{
		EmployeDTO emp = new EmployeDTO(1003,"kelly","Dunder Mifflin","Scranton");
		Mockito.when(employeeService.addEmploye(Mockito.any(EmployeDTO.class))).thenReturn("New row Created with employee Id "+emp.getEmpId());
		ObjectMapper mapper = new ObjectMapper();
		mapper.setSerializationInclusion(Include.NON_NULL);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/employees/").accept(MediaType.APPLICATION_JSON)
		.content(mapper.writeValueAsBytes(emp)).contentType(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		MockHttpServletResponse response = result.getResponse();
		Assert.assertEquals("New row Created with employee Id 1003" , response.getContentAsString());
		Assert.assertEquals(HttpStatus.CREATED.value(), response.getStatus());
	}
	
	
		@Test
		public void getEmployeeByIdInvalidTest() throws Exception
		{
			EmployeDTO emp = new EmployeDTO();
			Mockito.when(employeeService.getEmploye(1010)).thenReturn(emp);
//			ObjectMapper mapper = new ObjectMapper();
//			mapper.setSerializationInclusion(Include.NON_NULL);
//			RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/employees/getEmploye/1010").accept(MediaType.APPLICATION_JSON)
//			.content(mapper.writeValueAsBytes(1010)).contentType(MediaType.APPLICATION_JSON);
//				MvcResult result = mockMvc.perform(requestBuilder).andReturn();
//				MockHttpServletResponse response = result.getResponse();
//				Assert.assertEquals(HttpStatus.BAD_GATEWAY,response.getStatus());	
			RequestBuilder reqBuilder =  MockMvcRequestBuilders.get("/employees/getEmploye/1001").accept(MediaType.APPLICATION_JSON);
		
			MvcResult result = mockMvc.perform(reqBuilder).andReturn();
			MockHttpServletResponse response = result.getResponse();
			
			//Serialize employeeDTO to json format
			ObjectMapper mapper = new ObjectMapper();
			ByteArrayOutputStream out = new ByteArrayOutputStream();
			mapper.writeValue(out, emp);
			byte[] data = out.toByteArray();
			Assert.assertEquals(new String(data), response.getContentAsString());
			
			
		}
	

		@Test
		public void getEmployeeByIdValidTest() throws Exception
		{
			EmployeDTO emp = new EmployeDTO(1001,"Jim","Dunder Mifflin","Scranton");
			Mockito.when(employeeService.getEmploye(1001)).thenReturn(emp);
			
			RequestBuilder reqBuilder =  MockMvcRequestBuilders.get("/employees/getEmploye/1001").accept(MediaType.APPLICATION_JSON);
			MvcResult result = mockMvc.perform(reqBuilder).andReturn();
			MockHttpServletResponse response = result.getResponse();
			
			//Serialize employeeDTO to json format
			ObjectMapper mapper = new ObjectMapper();
			ByteArrayOutputStream out = new ByteArrayOutputStream();
			mapper.writeValue(out, emp);
			byte[] data = out.toByteArray();
			Assert.assertEquals(new String(data), response.getContentAsString());
			
		}
	
	
	
	@Test
	public void deleteEmployeeInvalidTest() throws Exception
	{
		Mockito.when(employeeService.removeEmploye(1010)).thenReturn("Employee "+1010+ " Does Not Exist");
		ObjectMapper mapper = new ObjectMapper();
		mapper.setSerializationInclusion(Include.NON_NULL);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/employees/1010").accept(MediaType.APPLICATION_JSON)
				.content(mapper.writeValueAsBytes(1010)).contentType(MediaType.APPLICATION_JSON);
				MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		MockHttpServletResponse response = result.getResponse();
		Assert.assertEquals("Employee "+1010+ " Does Not Exist",response.getContentAsString());	
	}
	
	
	@Test
	public void deleteEmployeeValidTest() throws Exception
	{
		Mockito.when(employeeService.removeEmploye(1006)).thenReturn( "Employee with "+1006+" deleted successfully");
		ObjectMapper mapper = new ObjectMapper();
		mapper.setSerializationInclusion(Include.NON_NULL);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/employees/1006").accept(MediaType.APPLICATION_JSON)
				.content(mapper.writeValueAsBytes(0)).contentType(MediaType.APPLICATION_JSON);
				MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		MockHttpServletResponse response = result.getResponse();
		Assert.assertEquals("Employee with "+1006+" deleted sucessfully",response.getContentAsString());	
	}
	
	

	
}
