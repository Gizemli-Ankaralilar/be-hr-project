package com.team1.rabbitmq.consumer;

import com.team1.rabbitmq.model.SaveAuthModel;
import com.team1.repository.entity.UserProfile;
import com.team1.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SaveAuthConsumer {

    private final UserService userProfileService;

    @RabbitListener(queues = "queue-auth")
    public void createFromQueue(SaveAuthModel model){
        userProfileService.saveRabbit(model);

//        userProfileService.save(
//                UserProfile.builder()
//                        .authId(model.getAuthId())
//                        .username(model.getUsername())
//                        .email(model.getEmail())
//                        .password(model.getPassword())
//                        .address(model.getEmail())
//                        .build()
//        );

    }
}
