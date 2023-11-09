package com.team1.service;


import com.team1.dto.request.SendMailRequestDto;
import com.team1.exception.ErrorType;
import com.team1.exception.MailManagerException;
import com.team1.rabbitmq.model.AdminConfirmMailModel;
import com.team1.rabbitmq.model.AuthMailModel;
import com.team1.rabbitmq.model.CompanyMailModel;
import com.team1.repository.IMailRepository;
import com.team1.repository.entity.MailProfile;
import com.team1.utility.ServiceManager;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;


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
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, "utf-8");

        try {
            helper.setFrom("${spring.mail.username}");
            helper.setTo(model.getEmail());
            helper.setSubject("AKTIVASYON KODU");

            String content = "<div style='text-align: center;'><h2 style='font-size: 24px;'>"
                    + model.getUsername() + "</h2>" +
                    "<p style='font-size: 18px;'>Başarıyla kayıt oldunuz.</p>" +
                    "<p><a href='http://localhost:9090/api/v1/auth/activate_status?token="
                    + model.getToken() + "'>" +
                    "<button style='background-color: #FFA500; border: none; color: white; padding: 12px 24px; text-align: center; text-decoration: "
                    + "none; display: inline-block; font-size: 16px; border-radius: 12px;'>Üyeliğini Aktive Et</button></a></p></div>";

            helper.setText(content, true);
            javaMailSender.send(message);
        } catch (MessagingException e) {
            throw new MailManagerException(ErrorType.BAD_REQUEST);
        }
    }


    public void createAdminConfirmMail(AdminConfirmMailModel model) {
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, "utf-8");

        try {
            helper.setFrom("${spring.mail.username}");
            helper.setTo(model.getAdminMail());
            helper.setSubject("AKTIVASYON KODU");

            String content = "<div style='text-align: center;'><h2 style='font-size: 24px;'>"
                    + "["+ model.getUsername() + "] isimli kullanıcı şirket kayıt onayı talep ediyor." + "</h2>" +
                    "<p style='font-size: 18px;'>Onay bekleyen şirket bilgileri:</p>" +
                    "<p style='font-size: 18px;'>Şirket İsmi: "+ model.getCompanyName() + "</p>" +
                    "<p style='font-size: 18px;'>Şirket Vergi Numarası: "+ model.getTaxNumber() + "</p>" +
                    "<p style='font-size: 18px;'>Şirket Adresi: "+ model.getAddress() + "</p>" +
                    "<p style='font-size: 18px;'>Şirket E-Posta: "+ model.getEmail() + "</p>" +
                    "<p style='font-size: 18px;'>Şirket Telefon Numarası: "+ model.getPhone() + "</p>" +
                    "<p><a href='http://localhost:9090/api/v1/auth/activate_company_status?token="
                    + model.getToken() + "'>" +
                    "<button style='background-color: #FFA500; border: none; color: white; padding: 12px 24px; text-align: center; text-decoration: "
                    + "none; display: inline-block; font-size: 16px; border-radius: 12px;'>Şirketin Üyeliğini Onayla</button></a></p></div>";

            helper.setText(content, true);
            javaMailSender.send(message);
        } catch (MessagingException e) {
            throw new MailManagerException(ErrorType.BAD_REQUEST);
        }
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
