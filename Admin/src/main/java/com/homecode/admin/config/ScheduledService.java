package com.homecode.admin.config;


import com.homecode.library.service.impl.CustomerUserServiceImpl;
import com.homecode.library.service.impl.EmailServiceImpl;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ScheduledService {

    private final EmailServiceImpl emailService;
    private final CustomerUserServiceImpl customerUserService;

    public ScheduledService(EmailServiceImpl emailService, CustomerUserServiceImpl customerUserService) {
        this.emailService = emailService;
        this.customerUserService = customerUserService;
    }

    @Scheduled(cron = "0 0 12 * * *")
    public void sendNewsletter() {
      emailService.sendEmailsForSubscribers();
    }

    @Scheduled(cron = "0 0 23 * * *")
    public void resetUserCredits() {
        this.customerUserService.dailyResetCredits();
    }
}
