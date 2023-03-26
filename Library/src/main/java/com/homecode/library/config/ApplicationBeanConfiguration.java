package com.homecode.library.config;

import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.spi.MappingContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Properties;

@Configuration
public class ApplicationBeanConfiguration {

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();


        modelMapper.addConverter(new Converter<String, LocalDateTime>() {
            @Override
            public LocalDateTime convert(MappingContext<String, LocalDateTime> mappingContext) {
                LocalDateTime parse = LocalDateTime.parse(mappingContext.getSource(),
                        DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
                return parse;
            }
        });


        return modelMapper;
    }


    @Bean
    public JavaMailSender javaMailSender(){

        JavaMailSenderImpl javaMailSender = new JavaMailSenderImpl();

        javaMailSender.setHost("smtp.gmail.com");
        javaMailSender.setPort(587);
        javaMailSender.setUsername("${EMAIL}");
        javaMailSender.setPassword("${PASSWORD}");
        javaMailSender.setJavaMailProperties(mailProperties());
        javaMailSender.setDefaultEncoding("UTF-8");

        return javaMailSender;

    }

    private Properties mailProperties(){
        Properties properties = new Properties();

        properties.setProperty("mail.smtp.auth","true");
        properties.setProperty("mail.transport.protocol","smtp");
        properties.put("mail.smtp.starttls.enable", "true");

        return properties;
    }

}
