package com.team1.service;


import com.team1.repository.IPermitRepository;
import com.team1.repository.entity.Permit;
import com.team1.utility.JwtTokenManager;
import com.team1.utility.ServiceManager;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PermitService extends ServiceManager<Permit, String> {

    private final JwtTokenManager jwtTokenManager;
    private final IPermitRepository permitRepository;


    public PermitService(IPermitRepository permitRepository, JwtTokenManager jwtTokenManager, IPermitRepository permitRepository1) {
        super(permitRepository);
        this.jwtTokenManager = jwtTokenManager;
        this.permitRepository = permitRepository1;
    }



}

