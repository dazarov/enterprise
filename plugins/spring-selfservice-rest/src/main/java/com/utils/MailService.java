package com.utils;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

public class MailService {
	
	private JavaMailSender mailSender;

	@Autowired
    public MailService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }
    
	public boolean sendMail(String from, String email, String subject, String text){
		MimeMessage message = mailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message);
		try {
			helper.setFrom(from);
			helper.setTo(email);
			helper.setSubject(subject);
			helper.setText(text);
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
        
        try{
            mailSender.send(message);
        }
        catch (MailException ex) {
            System.err.println(ex.getMessage());
            return false;
        }

		return true;
	}
}
