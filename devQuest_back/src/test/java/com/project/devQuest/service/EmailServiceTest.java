package com.project.devQuest.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class EmailServiceTest {

    @InjectMocks
    private EmailService emailService;

    @Mock
    private JavaMailSender javaMailSender;

    @Test
    public void whenSendVerificationEmail_thenEmailIsSent() {
        // Given
        String to = "user@example.com";
        String token = "verificationToken";

        // When
        emailService.sendVerificationEmail(to, token);

        // Then
        verify(javaMailSender, times(1)).send(any(SimpleMailMessage.class));
    }
}