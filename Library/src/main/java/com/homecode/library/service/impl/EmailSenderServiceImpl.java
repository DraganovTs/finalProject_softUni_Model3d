package com.homecode.library.service.impl;

import com.homecode.library.repository.EmailSubscriberRepository;
import com.homecode.library.service.EmailSenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailSenderServiceImpl implements EmailSenderService {

    private final JavaMailSender mailSender;
    private final EmailSubscriberRepository emailSubscriberRepository;

    @Autowired
    public EmailSenderServiceImpl(JavaMailSender mailSender, EmailSubscriberRepository emailSubscriberRepository) {
        this.mailSender = mailSender;
        this.emailSubscriberRepository = emailSubscriberRepository;
    }

    @Override
    public void sendEmail(String to, String subject, String message) {
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setFrom("");
        simpleMailMessage.setTo(to);
        simpleMailMessage.setSubject(subject);
        simpleMailMessage.setText(message);

        this.mailSender.send(simpleMailMessage);
    }
}
