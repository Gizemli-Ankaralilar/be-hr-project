package com.team1.repository;

import com.team1.repository.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IAuthRepository extends JpaRepository<User, Long> {


    Optional<User> findOptionalByUsernameAndPassword(String username, String password);

    Boolean existsByUsername(String username);

}
