package com.felix.felixapis.security.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Service
public class EmailServices {
    private JavaMailSender javaMailSender;

    @Autowired
    public EmailServices(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    public void sendMessageWithAttachment(final String from, final String to, final String subject, final String msg) throws MessagingException {
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, "UTF-8");
        helper.setText(msg, true);
        helper.setTo(to);
        helper.setSubject(subject);
        helper.setFrom(from);
        javaMailSender.send(message);
    }


}
