package org.jsp.UserBootApp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.context.config.ConfigDataResource;
import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;

@SpringBootApplication
public class UserBootAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(UserBootAppApplication.class, args);
	}

}
