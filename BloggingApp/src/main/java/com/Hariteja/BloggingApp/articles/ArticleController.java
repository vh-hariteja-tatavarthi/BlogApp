package com.Hariteja.BloggingApp.articles;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/articles")

public class ArticleController {

    @GetMapping("")
    String getArticles(){
        return "articles";
    }
}
