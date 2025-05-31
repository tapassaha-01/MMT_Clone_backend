package com.practice.MMT.Service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Objects;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    public void sendOtpEmail(String to, String otp) throws MessagingException, IOException {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);
        helper.setTo(to);
        helper.setSubject("Verify Your otp");
        try(var inputStream = Objects.requireNonNull(EmailService.class.getResourceAsStream("/templates/EmailContent.html"))){
            String template = new String(inputStream.readAllBytes(), StandardCharsets.UTF_8);

            String htmlContent = template.replace("${otp}", otp);

            helper.setText(htmlContent, true);
                    
        }
        mailSender.send(message);
    }
}