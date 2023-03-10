package com.example.database;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@EntityScan
@SpringBootApplication
public class DatabaseApplication {

	public static void main(String[] args) {
		try{
			SpringApplication.run(DatabaseApplication.class, args);
		}catch(Exception e){
			e.printStackTrace();
		}
	}

}
