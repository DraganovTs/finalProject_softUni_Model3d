package com.homecode.library.service.impl;

import com.homecode.library.model.EmailSubscribersEntity;
import com.homecode.library.model.dto.EmailDTO;
import com.homecode.library.repository.EmailSubscriberRepository;
import com.homecode.library.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmailServiceImpl implements EmailService {

  private final JavaMailSender mailSender;
    private final EmailSubscriberRepository emailSubscriberRepository;

    @Autowired
    public EmailServiceImpl(JavaMailSender mailSender, EmailSubscriberRepository emailSubscriberRepository) {
        this.mailSender = mailSender;
        this.emailSubscriberRepository = emailSubscriberRepository;
    }

    @Override
    public void sendEmail(String to, String subject, String message) {
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setFrom("model3d@domain.com");
        simpleMailMessage.setTo(to);
        simpleMailMessage.setSubject(subject);
        simpleMailMessage.setText(message);

       this.mailSender.send(simpleMailMessage);
    }

    @Override
    public void sendEmailsForSubscribers() {

        String subject = "Our new models today";
        String message = "You can check our new models here: http://localhost:8080/product-all";

        List<EmailSubscribersEntity> subscribersEmails = this.emailSubscriberRepository.findAll();

        subscribersEmails.forEach(email -> {
            sendEmail(email.getEmail(), subject, message);
        });


    }

    @Override
    public void saveEmail(EmailDTO emailDTO) {
        this.emailSubscriberRepository.save(new EmailSubscribersEntity().setEmail(emailDTO.getEmail()));
    }
}
