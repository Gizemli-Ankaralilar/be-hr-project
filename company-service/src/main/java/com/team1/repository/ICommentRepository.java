package com.team1.repository;

import com.team1.repository.entity.Comment;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ICommentRepository extends MongoRepository<Comment, String> {
}
