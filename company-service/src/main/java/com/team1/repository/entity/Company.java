package com.team1.repository.entity;


import com.team1.repository.enums.ERole;
import com.team1.repository.enums.EStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;


@AllArgsConstructor
@NoArgsConstructor
@Data
@SuperBuilder
@Document
public class Company extends BaseEntity{

    @Id
    private String id;
    private Long authId;
    private String companyName;
    private String username;
    private String email;
    private String password;
    private String taxNumber;
    private String companyEmail;
    private String companyAddress;
    private String companyPhoneNumber;
    private String activationCode;//şirket için oluşturulan activate kod hem veri tabanına eklenir hemde maili gönderilir.
    @Builder.Default
    private ERole role = ERole.COMPANY_OWNER;
    @Builder.Default
    private EStatus status = EStatus.INACTIVE;
    //@DBRef//Bir referans verir.Worker tablosunda bir referans tutar
    private List<String> workers;//Şirket de çalışan olduğu için çalışanın userId'sinin tutulduğu liste
    private List<String> finances;
    private List<String> permissions;
    private List<String> comments;
    //Giderler ayrı ayrı listelenebilir.Giderler tablosu oluşturulmak zorunda kalınabilir.
    //Finanse altında da listelenmesi gerekmemektedir.

}
