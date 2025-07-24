package com.Hariteja.BloggingApp.comments;

import org.springframework.stereotype.Service;

@Service
public class CommentService {
    public CommentRepository commentRepository;
    CommentService(CommentRepository commentRepository){
        this.commentRepository=commentRepository;
    }

}
