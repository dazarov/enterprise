package com.daniel.blog;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;


@Configuration
@ComponentScan({"com.daniel.blog", "com.daniel.blog.model", "com.daniel.blog.services"})
@EnableAutoConfiguration
@SpringBootApplication
//@ImportResource("spring.xml")
public class App {


	public static void main(String[] args) {
		SpringApplication.run(App.class, args);
	}
}