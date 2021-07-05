package com.employe.dto;

import java.util.Arrays;

import org.apache.log4j.Logger;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;



public class RestClient {
	
	private static final String GET_ALL_EMPLOYEES_API = "http://localhost:80/employees/getEmployees";
	static Logger logger = Logger.getLogger(RestClient.class);
	
	static RestTemplate restTemplate = new RestTemplate();
	
	public static void main(String args[])
	{
		callGetAllEmployeesAPI();
	}
	
	private static void callGetAllEmployeesAPI()
	{
		
		String res = restTemplate.getForObject(GET_ALL_EMPLOYEES_API, String.class); 
		logger.info(res);
		
		
//		HttpHeaders headers = new HttpHeaders();
//		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
//		
//		HttpEntity<String> entity = new HttpEntity<>("parameters",headers);
//		 ResponseEntity<String> res = restTemplate.exchange(GET_ALL_EMPLOYEES_API, HttpMethod.GET, entity, String.class);
//		 
//		logger.info(res);
	
	}
}
