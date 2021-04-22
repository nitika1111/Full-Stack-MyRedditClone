package com.nitika.myredditapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;
import org.springframework.scheduling.annotation.EnableAsync;

import com.nitika.myredditapp.config.SwaggerConfig;

@SpringBootApplication
@EnableAsync
@Import(SwaggerConfig.class)
public class MyredditappApplication {

	public static void main(String[] args) {
		SpringApplication.run(MyredditappApplication.class, args);
	}

}
