package com.team1.service;

import com.team1.rabbitmq.model.MailRegisterModel;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class MailService{

    private final JavaMailSender javaMailSender;

    public void sendRegisterUsersInfo(MailRegisterModel mailRegisterModel){
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setFrom("${spring.mail.username}"); //sunucu olarak kullanılacak mail
        mailMessage.setTo(mailRegisterModel.getEmail()); //kullanıcının girmiş olduğu mail
        mailMessage.setSubject("AKTIVASYON KODU");
        mailMessage.setText(
                mailRegisterModel.getUsername()  + "\nBaşarıyla kayıt oldunuz.\n" +
                        "Aktivasyon Link: " + "http://localhost:9090/api/v1/auth/activate_status?token="+mailRegisterModel.getToken()

        );
        javaMailSender.send(mailMessage);
    }


    }

