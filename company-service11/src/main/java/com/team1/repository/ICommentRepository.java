package com.team1.repository;

import com.team1.repository.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ICommentRepository extends JpaRepository<Comment, String> {
}
