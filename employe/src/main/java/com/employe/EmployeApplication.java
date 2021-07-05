package com.employe;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import com.employe.dto.EmployeDTO;
import com.employe.service.EmployeService;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@SpringBootApplication
public class EmployeApplication implements CommandLineRunner{

	static Logger logger = Logger.getLogger(EmployeApplication.class);
	@Autowired
	ApplicationContext context;
	@Autowired
	EmployeService service;
	public static void main(String[] args) {
		SpringApplication.run(EmployeApplication.class, args);
	}
	@Override
	public void run(String... args) throws Exception {
		EmployeDTO e1 = new EmployeDTO(1001,"Jim","Dunder Mifflin","Scranton");
		service.addEmploye(e1);
		logger.info("New record added");
	}

}
