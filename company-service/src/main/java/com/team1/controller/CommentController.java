package com.team1.controller;

import com.team1.dto.request.CommentDto;
import com.team1.repository.entity.Comment;
import com.team1.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.team1.constant.EndPoints.*;
@RestController
@RequestMapping(COMMENT)
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @PostMapping("/comment-create")
    public ResponseEntity<Boolean> createWorker(@RequestParam String token, @RequestBody CommentDto dto){
        return ResponseEntity.ok(commentService.createCommentUser(token, dto));
    }


    @GetMapping(FINDALL_COMMENT)
    public ResponseEntity<List<Comment>> findAllWorker(){
        return ResponseEntity.ok(commentService.findAllComment());
    }

    @DeleteMapping(DELETE_BY_ID_COMMENT)
    public ResponseEntity<Boolean> deleteComment(String token, String userId){
        return ResponseEntity.ok(commentService.deleteComment(token, userId));
    }

}
