package com.team1.service;

import com.team1.rabbitmq.model.MailRegisterModel;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;


@Service
@RequiredArgsConstructor
public class MailService{

    private final JavaMailSender javaMailSender;

/*    public void sendRegisterUsersInfo(MailRegisterModel mailRegisterModel){
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setFrom("${spring.mail.username}");
        mailMessage.setTo(mailRegisterModel.getEmail());
        mailMessage.setSubject("AKTIVASYON KODU");
        mailMessage.setText(
                mailRegisterModel.getUsername()  + "\nBaşarıyla kayıt oldunuz.\n" +
                        "Aktivasyon Link: \n" + "http://localhost:9090/api/v1/auth/activate_status?token="+mailRegisterModel.getToken()

        );
        javaMailSender.send(mailMessage);
    }*/


    public void sendRegisterUsersInfo(MailRegisterModel mailRegisterModel) {
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, "utf-8");

        try {
            helper.setFrom("${spring.mail.username}");
            helper.setTo(mailRegisterModel.getEmail());
            helper.setSubject("AKTIVASYON KODU");

            String content = "<div style='text-align: center;'><h2 style='font-size: 24px;'>" + mailRegisterModel.getUsername() + ",</h2>" +
                    "<p style='font-size: 18px;'>Başarıyla kayıt oldunuz.</p>" +
                    "<p><a href='http://localhost:9090/api/v1/auth/activate_status?token=" + mailRegisterModel.getToken() + "'>" +
                    "<button style='background-color: #FFA500; border: none; color: white; padding: 12px 24px; text-align: center; text-decoration: none; display: inline-block; font-size: 16px; border-radius: 12px;'>Aktivasyon Linki</button></a></p></div>";

            helper.setText(content, true);
            javaMailSender.send(message);
        } catch (MessagingException e) {
            // Handle the exception here
        }
    }

}

