package com.dxhh.elearning.services.impl;

import com.dxhh.elearning.services.EmailService;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class EmailServiceImpl implements EmailService {
    private final JavaMailSender javaMailSender;

    @Autowired
    public EmailServiceImpl(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    @Async
    public void sendTextEmail(String to, String subject, String text) {
        sendEmail(to, subject, text, false);
    }

    @Async
    public void sendHtmlEmail(String to, String subject, String htmlContent) {
        sendEmail(to, subject, htmlContent, true);
    }

    private void sendEmail(String to, String subject, String content, boolean isHtml) {
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);
        try {
            helper.setFrom("qthcsdlhung2059@gmail.com");
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(content, isHtml);

            javaMailSender.send(message);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}
