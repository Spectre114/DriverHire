package com.drive.hire.driverhire;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.mongodb.autoconfigure.MongoAutoConfiguration;

@SpringBootApplication(exclude = {
		MongoAutoConfiguration.class,
})
public class DriverhireApplication {

	public static void main(String[] args) {
		SpringApplication.run(DriverhireApplication.class, args);
	}

}
