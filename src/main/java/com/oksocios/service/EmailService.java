package com.oksocios.service;


import com.oksocios.model.Email;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    private final JavaMailSender javaMailSender;

    public EmailService(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    public void sendMail(Email email) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(email.getEmailTo());
        mailMessage.setSubject(email.getSubject());
        mailMessage.setText(email.getMessage());
        javaMailSender.send(mailMessage);
    }
}
