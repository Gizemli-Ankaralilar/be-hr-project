package com.team1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class EmailServiceApplication {


    /*final JavaMailSender javaMailSender;

    public EmailServiceApplication(JavaMailSender javaMailSender){
        this.javaMailSender = javaMailSender;
    }*/


    public static void main(String[] args) {
        SpringApplication.run(EmailServiceApplication.class);
    }

    /*@EventListener(ApplicationReadyEvent.class)
    public void sendMail(){
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setFrom("musty1406@gmail.com");
        mailMessage.setTo("z.hilaler1@gmail.com");
        mailMessage.setSubject("Aktivasyon Kodunuz: ");
        mailMessage.setText("Af259");
        javaMailSender.send(mailMessage);
    }*/














}