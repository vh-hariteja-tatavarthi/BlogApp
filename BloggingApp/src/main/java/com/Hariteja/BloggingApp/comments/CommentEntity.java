package com.Hariteja.BloggingApp.comments;

import com.Hariteja.BloggingApp.articles.ArticleEntity;
import com.Hariteja.BloggingApp.users.UserEntity;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;

import java.util.Date;

@Getter
@Setter
@ToString
//@RequiredArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity(name="comments")
public class CommentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(nullable = false)
    private Long id;

    @Column(nullable = false)
    private String body;

    @CreatedDate
    private Date createdAt;

    @ManyToOne
    @JoinColumn(name="articleId", nullable = false)
    private ArticleEntity article;

    @ManyToOne
    @JoinColumn(name="authorId", nullable = false)
    private UserEntity author;


}
