package com.team1.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

@Getter
@AllArgsConstructor
public enum ErrorType {

    INTERNAL_ERROR_SERVER(5100,"Sunucu Hatası",HttpStatus.INTERNAL_SERVER_ERROR),
    BAD_REQUEST(4100,"Parametre hatası",HttpStatus.BAD_REQUEST),
    USER_NOT_FOUND(4110, "Böyle bir kullanıcı bulunamadı",HttpStatus.NOT_FOUND),
    ACCOUNT_NOT_ACTIVE(4111, "Hesabınız Aktif değildir",HttpStatus.BAD_REQUEST),
    INVALID_CODE(4112, "Geçersiz Kod",HttpStatus.BAD_REQUEST),
    ALREADY_ACTIVE(4113, "Hesabınız zaten aktif",HttpStatus.BAD_REQUEST),
    UNEXPECTED_ERROR(4114,"Beklenmeyen bir hata olustu",HttpStatus.BAD_REQUEST),
    USERNAME_ALREADY_EXIST(4115,"Böyle bir kullanıcı adı bulunmaktadır !!!",HttpStatus.BAD_REQUEST),
    DATA_INTEGRITY(4116, "hatalı veri",HttpStatus.BAD_REQUEST),
    LOGIN_ERROR(4117, "Kullanıcı adı veya şifre hatalı!!!",HttpStatus.BAD_REQUEST),
    TOKEN_NOT_CREATED(4119, "Token Oluşturulamadı !!!",HttpStatus.BAD_REQUEST),


    BAD_REQUEST_ERROR(1201, "Geçersiz Parametre Girişi Yaptınız",HttpStatus.BAD_REQUEST),
    AUTH_PASSWORD_ERROR(1301, "Şifreler uyuşmuyor.", HttpStatus.BAD_REQUEST),
    AUTH_EMAIL_ERROR(1302, "Bu e-posta zaten kayıtlı.", HttpStatus.BAD_REQUEST),
    INTERNAL_ERROR(3000, "Sunucuda beklenmeyen hata", INTERNAL_SERVER_ERROR),
    KULLANICI_BULUNAMADI(2301, "Aradığınız id ye ait kullanıcı bulunamadı.", INTERNAL_SERVER_ERROR),
    INVALID_TOKEN(4001, "GEÇERSİZ TOKEN BİLGİSİ", HttpStatus.BAD_REQUEST),
    AUTH_LOGIN_ERROR(4002, "KULLANICI ADI VEYA ŞİFRE HATALIDIR.", INTERNAL_SERVER_ERROR),
    COMPANY_HAS_BEEN(4003, "COMPANY_HAS_BEEN", INTERNAL_SERVER_ERROR),
    COMPANY_NOT_FOUND(4004,"Şirket bulunamadı.", HttpStatus.NOT_FOUND),
    DUPLICATE_TAX_NUMBER(4005, "Bu şirket sistemde zaten kayıtlıdır.",HttpStatus.BAD_REQUEST),



    WORKER_NOT_FOUND(5000,"Çalışan bulunamadı.", HttpStatus.NOT_FOUND);




    ;


    private int code;
    private String message;
    HttpStatus httpStatus;


}
