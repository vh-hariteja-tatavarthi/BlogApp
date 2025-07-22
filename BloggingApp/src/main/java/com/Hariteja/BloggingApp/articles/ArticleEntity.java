package com.Hariteja.BloggingApp.articles;


import com.Hariteja.BloggingApp.users.UserEntity;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@ToString
//@RequiredArgsConstructor
@NoArgsConstructor (access = AccessLevel.PROTECTED)
@Entity(name= "articles")
public class ArticleEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;

    @NonNull
    private String title;

    @NonNull
    private String slug;

    @Nullable
    private String subtitle;

    @NonNull
    private String body;

    @CreatedDate
    private Date createdAt;

    //private List<String> tags;

    @ManyToOne
    @JoinColumn(name="authorId", nullable = false)
    private UserEntity author;
}
