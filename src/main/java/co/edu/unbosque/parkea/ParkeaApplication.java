package co.edu.unbosque.parkea;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class ParkeaApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(ParkeaApplication.class, args);
	}

	@Override
	protected SpringApplicationBuilder configure (SpringApplicationBuilder applicationBuilder){
		return applicationBuilder.sources(ParkeaApplication.class);
	}
}
