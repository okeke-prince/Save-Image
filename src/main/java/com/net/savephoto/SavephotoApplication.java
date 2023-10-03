package com.net.savephoto;

import lombok.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.sql.DataSource;

@SpringBootApplication
public class SavephotoApplication {

	public static void main(String[] args) {
		SpringApplication.run(SavephotoApplication.class, args);
	}

}
