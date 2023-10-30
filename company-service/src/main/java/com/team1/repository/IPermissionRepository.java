package com.team1.repository;

import com.team1.repository.entity.Permission;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface IPermissionRepository extends MongoRepository<Permission, String> {
}
