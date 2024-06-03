package com.srirama.palindrome;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

/**
 * The main class to start the spring boot application
 */
@EnableCaching
@SpringBootApplication
public class MyPalindromeApp {

	public static void main(String[] args) {
		SpringApplication.run(MyPalindromeApp.class, args);
	}


}
