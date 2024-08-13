package com.project.devQuest.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender javaMailSender;

    public void sendVerificationEmail(String to, String token) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject("Vérification de compte");
        message.setText("Cliquez sur ce lien pour vérifier votre compte : "
                + "http://localhost:4200/verify?token=" + token);
        javaMailSender.send(message);
    }

}
