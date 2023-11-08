package com.team1.service;

import com.team1.dto.request.RegisterRequestCompanyDto;
import org.springframework.stereotype.Service;

@Service
public class AdminService {
    public Boolean approvedCompanyOwner(RegisterRequestCompanyDto dto) {

        boolean companyApproved = false;

        if (companyApproved){
            return true;
        }else{
            return false;
        }

    }


    public Boolean approvedComment(){

        boolean commentApproved = false;

        if (commentApproved){
            return true;
        }else{
            return false;
        }

        /*
        Yorumu kim yapabilirdi, worker
        o zaman worker service olan method sayesinde yapilan methodlar
        donup gelip bu methoda dusecek yada bu methodun yeri degisebilir mi bilmiyorum
        sonuc boyle bir methoda dusecek ve admine
        yorumun icerigi worker bilgileri gelecek yani bu methoda
        sonuc olarak burada onaylanacak ve bu onay sayesinde bu yorum nasil yayinlanacak
        fiti fiti
         */

    }

}
