package co.carlex.microservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "co.carlex.microservice.repository")
public class MicroserviceSpringbootImplApplication {

	public static void main(String[] args) {
		SpringApplication.run(MicroserviceSpringbootImplApplication.class, args);
	}

}
