package com.homecode.admin.config;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ScheduledService {

    private Logger LOGGER = LoggerFactory.getLogger(ScheduledService.class);

    @Scheduled(cron = "0 0 0 ? * * *")
    public void sendNewsletter(){
        LOGGER.info("test");
    }
}
