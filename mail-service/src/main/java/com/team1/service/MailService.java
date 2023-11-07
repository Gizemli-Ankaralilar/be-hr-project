package com.team1.service;


import com.team1.dto.request.SendMailRequestDto;
import com.team1.rabbitmq.model.AuthMailModel;
import com.team1.rabbitmq.model.CompanyMailModel;
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

    public void createAuthMail(AuthMailModel model) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setFrom("${spring.mail.username}");//gönderdiğimiz mail adresi
        mailMessage.setTo(model.getEmail());//gidecek mail adresi
        mailMessage.setSubject("AKTIVASYON KODU");//KONU
        mailMessage.setText(
                model.getUsername()  + "\nBaşarıyla kayıt oldunuz.\n" +
                        "Aktivasyon Link: \n" + "http://localhost:9090/api/v1/auth/activate_status?token="+model.getToken()

        );
        javaMailSender.send(mailMessage);
    }

    public void createCompanyMail(CompanyMailModel model) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setFrom("${spring.mail.username}");
        mailMessage.setTo(model.getMail());
        mailMessage.setSubject("KULLANICI BİLGİLERİ");
        mailMessage.setText((model.getNewMail() + "\nŞirket mail adresiniz ve passwordunuz.\n" + model.getPassword()

                ));
        javaMailSender.send(mailMessage);
    }
}
