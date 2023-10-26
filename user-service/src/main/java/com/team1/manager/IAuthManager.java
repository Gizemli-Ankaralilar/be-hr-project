package com.team1.manager;

import org.springframework.cloud.openfeign.FeignClient;


@FeignClient(url = "http://localhost:7071/api/v1/auth",decode404 = true,name = "userprofile-auth")
public interface IAuthManager {



}
