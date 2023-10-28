package com.team1.repository;


import com.team1.repository.entity.UserProfile;
import org.springframework.data.jpa.repository.JpaRepository;


public interface IUserRepository extends JpaRepository<UserProfile, Long> {


}
