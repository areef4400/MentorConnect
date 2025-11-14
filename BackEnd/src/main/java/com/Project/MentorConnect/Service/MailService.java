package com.Project.MentorConnect.Service;

import lombok.RequiredArgsConstructor;
import org.hibernate.service.spi.ServiceException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MailService {

    private final JavaMailSender javaMailSender;

    public boolean sendMail(String to, String from, String text, String subject){
        try{
            SimpleMailMessage message = new SimpleMailMessage();

            message.setText(text);
            message.setTo(to);
            message.setFrom(from);
            message.setSubject(subject);

            javaMailSender.send(message);
            return true;
        }catch (Exception e){
            throw new ServiceException(e.getMessage());
        }
    }

}
