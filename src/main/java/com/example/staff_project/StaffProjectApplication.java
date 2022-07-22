package com.example.staff_project;

import com.example.staff_project.serviceImpl.StaffServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class StaffProjectApplication implements CommandLineRunner {

	@Autowired
	private StaffServiceImpl staffService;

	public static void main(String[] args) {
		SpringApplication.run(StaffProjectApplication.class, args);
	}

	@Override
	public void run(String ...args) throws Exception{
	}

}
