package com.mckesson;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication
@ImportResource("beans.xml")
public class Application {
	
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
	
//	@Bean
//	public JavaMailSenderImpl mailSender(){
//		JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
//		mailSender.setHost("smtp.gmail.com");
//		mailSender.setPort(587);
//		mailSender.setUsername("testJavaMail2000@gmail.com");
//		mailSender.setPassword("tSup4445");
//		Properties props = new Properties();
//		props.put("mail.smtp.auth", true);
//		props.put("mail.smtp.starttls.enable", true);
//		mailSender.setJavaMailProperties(props);
//		return mailSender;
//	}
//	
//	@Bean
//	public MailService mailService(){
//		return new MailService(mailSender());
//	}
//	
//	@Bean
//	public SelfServiceController selfeServiceController(){
//		return new SelfServiceController(mailService(), userService());
//	}
//	
//	@Bean
//	public OdmPersonDaoImpl userService(){
//		return new OdmPersonDaoImpl(ldapTemplate());
//	}
	
}
