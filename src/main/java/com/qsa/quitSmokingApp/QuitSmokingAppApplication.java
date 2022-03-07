package com.qsa.quitSmokingApp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {
		org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration.class,

})
public class QuitSmokingAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(QuitSmokingAppApplication.class, args);
	}

}
