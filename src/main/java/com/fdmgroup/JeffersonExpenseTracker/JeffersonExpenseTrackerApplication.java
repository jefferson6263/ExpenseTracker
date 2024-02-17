package com.fdmgroup.JeffersonExpenseTracker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import com.fdmgroup.JeffersonExpenseTracker.Security.RsaKeyProperties;

@EnableConfigurationProperties(RsaKeyProperties.class)
@SpringBootApplication
public class JeffersonExpenseTrackerApplication {

	public static void main(String[] args) {
		SpringApplication.run(JeffersonExpenseTrackerApplication.class, args);
		System.out.println("_______________________________________");
	}

}
  