package com.team1.repository.entity;

import com.team1.repository.enums.EWishStatus;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserWish {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String wishDescription;

    @Enumerated(EnumType.STRING)
    private EWishStatus wishStatus = EWishStatus.PENDING;

    @ManyToOne
    @JoinColumn(name = "sender_id")
    private UserProfile sender;

    @ManyToOne
    @JoinColumn(name = "receiver_id")
    private UserProfile receiver;

    private LocalDateTime wishTime = LocalDateTime.now();
    private LocalDateTime approvalTime;


    @PreUpdate
    protected void onUpdate() {
        if (wishStatus != null && wishStatus != EWishStatus.PENDING) {
            approvalTime = LocalDateTime.now();
        }
    }
}