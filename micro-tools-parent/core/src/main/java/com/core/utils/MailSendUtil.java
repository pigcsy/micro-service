package com.core.utils;

import org.springframework.beans.factory.annotation.Value;

public class MailSendUtil {

    private final String SSL_FACTORY = "javax.net.ssl.SSLSocketFactory";
    @Value("${application.name:/unkonw}")
    private String smtpServer;
    @Value("${application.name:/unkonw}")
    private String emailPort;

//    public void sendSimpleMail(String emailFrom, String sendTo, String titel, String content) {  
//        SimpleMailMessage message = new SimpleMailMessage();  
//        message.setFrom(emailFrom);  
//        message.setTo(sendTo);  
//        message.setSubject(titel);  
//        message.setText(content);  
//        mailSender.send(message);  
//    }  
}