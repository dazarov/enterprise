package com.daniel.blog;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

/**
 * Hello world!
 *
 */
@Configuration
@ComponentScan
@EnableAutoConfiguration
@SpringBootApplication
@ImportResource("spring.xml")
public class App {


	public static void main(String[] args) {
		SpringApplication.run(App.class, args);
	}
}