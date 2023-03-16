package com.homecode.library.service;

import com.homecode.library.model.dto.EmailDTO;

public interface EmailService {

    void sendEmail(String to, String subject, String message);

    void sendEmailsForSubscribers();

    void saveEmail(EmailDTO emailDTO);
}
