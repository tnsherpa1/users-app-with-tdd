package com.example.usersapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class UsersApplication {

	@RequestMapping("/")
	public String home() {
		return "some users";
	}

	public static void main(String[] args) {
		SpringApplication.run(UsersApplication.class, args);
	}
}
