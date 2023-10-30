package com.team1.repository.entity;

import com.team1.repository.enums.EPermission;
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
public class Permission extends BaseEntity {

    @Id
    private String id;
    private String companyId;
    private String workerId;
    @Builder.Default
    private EPermission permission = EPermission.NO_LEAVE;
    private List<String> breakTime;
}
