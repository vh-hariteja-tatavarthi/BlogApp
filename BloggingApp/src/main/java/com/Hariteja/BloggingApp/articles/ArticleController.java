package com.Hariteja.BloggingApp.articles;

import com.Hariteja.BloggingApp.users.UserEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/articles")

public class ArticleController {

    @GetMapping("")
    String getArticles() {
        return "articles";
    }

    @PostMapping("")
    String createArticle(@AuthenticationPrincipal UserEntity user){
        return "created by" + user.getUsername();
    }
}
