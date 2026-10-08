package com.drive.hire.driverhire;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.mongodb.autoconfigure.MongoAutoConfiguration;

@SpringBootApplication(exclude = {
		MongoAutoConfiguration.class,
})
public class DriverhireApplication {

	public static void main(String[] args) {
//		ConfigurableApplicationContext context =
		SpringApplication.run(DriverhireApplication.class, args);
//		DriverRepository driverRepository = context.getBean(DriverRepository.class);
//		Driver driver = Driver.builder()
//				.id(1)
//				.name("Kartick")
//				.pass("122345")
//				.phNum(12345)
//				.build();
//		driverRepository.insertDriver(driver);
//        List<Driver> fetchDriver = driverRepository.getDriver(1);
//		System.out.println(fetchDriver);
	}

}
