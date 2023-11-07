package com.team1.service;


import com.team1.dto.request.SendMailRequestDto;
import com.team1.repository.IMailRepository;
import com.team1.repository.entity.MailProfile;
import com.team1.utility.ServiceManager;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;


@Service
public class MailService extends ServiceManager<MailProfile, Long> {

    private final IMailRepository iMailRepository;
    private final JavaMailSender javaMailSender;

    public MailService(IMailRepository iMailRepository, JavaMailSender javaMailSender) {
        super(iMailRepository);
        this.iMailRepository = iMailRepository;
        this.javaMailSender = javaMailSender;
    }

    public String sendMail(SendMailRequestDto dto){
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setFrom("${spring.mail.username}");
        mailMessage.setTo(dto.getEmail());
        mailMessage.setSubject("AKTIVASYON KODU");
        mailMessage.setText(
                dto.getUsername()  + "\nBaşarıyla kayıt oldunuz.\n" +
                        "Aktivasyon Link: \n" + "http://localhost:9090/api/v1/auth/activate_status?token="+dto.getToken()

        );
        javaMailSender.send(mailMessage);
        return "Aktivasyon icin, mail adresinizi kontrol ediniz!!";
    }
}
