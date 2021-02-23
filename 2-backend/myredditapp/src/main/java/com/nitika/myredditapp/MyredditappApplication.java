package com.nitika.myredditapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class MyredditappApplication {

	public static void main(String[] args) {
		SpringApplication.run(MyredditappApplication.class, args);
	}

}
