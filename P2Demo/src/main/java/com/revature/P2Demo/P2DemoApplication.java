package com.revature.P2Demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan("com.revature.model")
@ComponentScan("com.revature") // this tells Spring to look in com.revature for beans (any stereotype annotations)
@EnableJpaRepositories("com.revature.repository") // This tells the Spring to look for JPA Repository
public class P2DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(P2DemoApplication.class, args);

		System.out.println("Welcome to the Team and Player Management System!");

	}

}
