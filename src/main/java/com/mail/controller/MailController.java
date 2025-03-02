package com.mail.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mail.pojo.MailRequest;
import com.mail.service.MailService;

@RestController
@RequestMapping("/api/mail")
public class MailController {

    @Autowired
    private MailService mailService;

    @PostMapping("/simpleMailMessage")
    public ResponseEntity<String> sendMail(@RequestBody MailRequest request) {
        mailService.sendEmail(request);
        return ResponseEntity.ok("Mail sent to: " + request.getTo());
    }
    
    @PostMapping("/mimeMessageHelper")
    public ResponseEntity<Map<String, String>> sendHtmlMail(@RequestBody MailRequest request){
        mailService.sendHtmlEmail(request.getTo(), request.getSubject());
        Map<String, String> response = new HashMap<>();
        response.put("message", "HTML Mail Sent Successfully");
        return ResponseEntity.ok(response);
    }
}