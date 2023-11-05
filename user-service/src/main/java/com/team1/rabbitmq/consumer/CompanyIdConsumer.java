package com.team1.rabbitmq.consumer;

import com.team1.dto.response.CompanyIdResponseDto;
import com.team1.rabbitmq.model.CompanyIdModel;
import com.team1.repository.entity.UserProfile;
import com.team1.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CompanyIdConsumer {

    UserService userService;
    @RabbitListener(queues =  "queue-user-id")
    public Object convertSendAndReceiveId(CompanyIdModel model) {
        System.out.println("Kuyruğa düşütü");
        Optional<UserProfile> userProfile = userService.findByCompanyId(model.getAuthId());
        CompanyIdResponseDto companyIdResponseDto = CompanyIdResponseDto.builder().companyId(userProfile.get().getCompanyId()).build();
        return companyIdResponseDto;
    }
}
