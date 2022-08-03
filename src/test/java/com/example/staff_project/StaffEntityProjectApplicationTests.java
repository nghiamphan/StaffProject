package com.example.staff_project;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.env.Environment;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test")
class StaffEntityProjectApplicationTests {

	@Autowired
	Environment environment;
	@Test
	void contextLoads() {
		System.out.println("Get a property written by Jenkinsfile: " + environment.getProperty("prop1"));
	}

}
