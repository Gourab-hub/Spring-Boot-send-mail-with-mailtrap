package com.mail.service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.mail.pojo.MailRequest;

@Service
public class MailService{

	@Autowired
	private JavaMailSender mailSender;

	public void sendEmail(MailRequest request) {
		SimpleMailMessage message = new SimpleMailMessage();
		message.setTo(request.getTo());
		message.setSubject(request.getSubject());
		message.setText(request.getMessage());
		message.setFrom("scm@demomailtrap.com");
		mailSender.send(message);
		System.out.println("Mail Sent Successfully!");
	}
	
	
	public void sendHtmlEmail(String to, String subject) {
	    try {
	        MimeMessage message = mailSender.createMimeMessage();
	        ClassPathResource htmlResource = new ClassPathResource("templates/mail.html");
	        String htmlContent = new String(Files.readAllBytes(htmlResource.getFile().toPath()));
	        MimeMessageHelper helper = new MimeMessageHelper(message, true);
	        helper.setFrom("sandbox@demomailtrap.com");
	        helper.setTo(to);
	        helper.setSubject(subject);
	        helper.setText(htmlContent, true);
	        File image = new File("src/main/resources/static/world.png");
	        helper.addInline("logo", image);
	        mailSender.send(message);
	        System.out.println("HTML Email Sent Successfully!");
	    } catch (MessagingException | IOException e) {
	        System.err.println("Email Sending Failed: " + e.getMessage());
	    }
	}
}


