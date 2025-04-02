package com.devops;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.stream.IntStream;

@SpringBootApplication(scanBasePackages = "com.devops")
public class DevopsApplication {

	public static void main(String[] args) {
		SpringApplication.run(DevopsApplication.class, args);
		System.out.println("This is a demo app for DevOps practice");

	}

}
