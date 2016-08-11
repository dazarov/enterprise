package com.daniel.blog;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;


@Configuration
@ComponentScan
@EnableAutoConfiguration
@SpringBootApplication
//@ContextConfiguration(classes = {PersistenceContext.class})
//@ImportResource("spring.xml")
public class App {


	public static void main(String[] args) {
		SpringApplication.run(App.class, args);
	}
}