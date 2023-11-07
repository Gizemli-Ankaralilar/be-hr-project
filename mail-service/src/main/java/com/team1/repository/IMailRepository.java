package com.team1.repository;


import com.team1.repository.entity.MailProfile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IMailRepository extends JpaRepository<MailProfile, Long> {


}
