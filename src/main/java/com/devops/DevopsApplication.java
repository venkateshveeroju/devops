package com.devops;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.stream.IntStream;

@SpringBootApplication(scanBasePackages = "com.devops")
public class DevopsApplication {

	public static void main(String[] args) {
		SpringApplication.run(DevopsApplication.class, args);
		System.out.println("This is a demo app for DevOps practice");
		int array[] = {1, 2, 3, 4, 5, 6, 7, 8};
		IntStream.range(0, array.length).filter(i -> i % 2 == 0).map(i -> array[i]).forEach(System.out::println);

	}

}
