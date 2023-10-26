package com.team1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MailServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(MailServiceApplication.class);
    }


/*    @Autowired
   private JavaMailSender javaMailSender;

    @EventListener(ApplicationReadyEvent.class)
    public  void sendMail(){
        SimpleMailMessage mailMessage=new SimpleMailMessage();
        mailMessage.setFrom("amine.gnyk@gmail.com");//${java10_mail}
        mailMessage.setTo("hrproject@gmail.com");
        mailMessage.setSubject("AKTIVASYON KODUNUZ...");
       mailMessage.setText("lkD345");
        javaMailSender.send(mailMessage);
    }*/

}