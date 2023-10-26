package com.joved;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class JovedApplication {

	public static void main(String[] args) {
		
		SpringApplication.run(JovedApplication.class, args);
		
	}

	/*
@Bean
public CommandLineRunner demo(UserRepository repository) {
	return (args) -> {
		System.out.println("BUSCANDO:");
		repository.findByUsername("paulitaloquita").forEach(user ->
		{
			System.out.println("HOLA");
			System.out.println(user.toString());
		});
	
	};
}*/
}
