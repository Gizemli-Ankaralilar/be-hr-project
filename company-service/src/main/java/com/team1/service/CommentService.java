package com.team1.service;

import com.team1.dto.request.CommentDto;
import com.team1.exception.CompanyException;
import com.team1.exception.ErrorType;
import com.team1.repository.ICommentRepository;
import com.team1.repository.entity.Comment;
import com.team1.repository.entity.Company;
import com.team1.utility.JwtTokenManager;
import com.team1.utility.ServiceManager;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CommentService extends ServiceManager<Comment, String> {

    private final JwtTokenManager jwtTokenManager;
    private final ICommentRepository commentRepository;
    private Company company;

    public CommentService(ICommentRepository commentRepository, JwtTokenManager jwtTokenManager) {
        super(commentRepository);
        this.jwtTokenManager = jwtTokenManager;
        this.commentRepository = commentRepository;
    }

    public Boolean createCommentUser(String token, CommentDto dto){
        String companyId = jwtTokenManager.getCompanyIdFromToken(token).orElseThrow(() -> {
            throw new CompanyException(ErrorType.INVALID_TOKEN);
        });
        Comment comment = Comment.builder().companyId(companyId).comment(dto.getComment()).build();
        //auth tablosuna kayıt eklenmedi!!!!
        save(comment);
        List<String> id = new ArrayList<>();
        if (company.getId().equals(companyId)) {//bu metot olmasa da olabilir.
            id.add(comment.getId());
            company.setComments(id);
        }
        return true;
    }

    public List<Comment> findAllComment() {
        return commentRepository.findAll();
    }

    public Boolean deleteComment(String token, String userId){//workerId frontand tarafından alınacak
        String companyId = jwtTokenManager.getCompanyIdFromToken(token).orElseThrow(() -> {
            throw new CompanyException(ErrorType.INVALID_TOKEN);
        });
        Optional<Comment> comment = commentRepository.findById(userId);
        if (comment.get().getCompanyId().equals(companyId)) {
            commentRepository.delete(comment.get());
            return true;
        }
        return false;
    }

}

