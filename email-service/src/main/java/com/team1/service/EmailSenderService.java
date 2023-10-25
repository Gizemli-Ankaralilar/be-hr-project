package com.team1.service;

import com.team1.entity.Email;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailSenderService {

    // private final JavaMailSender javaMailSender;

    public void sendMail(Email model){
        SimpleMailMessage mailMessage=new SimpleMailMessage();
        mailMessage.setFrom("");
        mailMessage.setTo(model.getEmail());
        mailMessage.setSubject("Geçici şifre: ");
        mailMessage.setText(model.getPassword());
        javaMailSender.send(mailMessage);
    }
}
