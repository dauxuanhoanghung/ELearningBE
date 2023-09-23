package com.dxhh.elearning.services;

public interface EmailService {
    void sendTextEmail(String to, String subject, String text);
    void sendHtmlEmail(String to, String subject, String htmlContent);
}
