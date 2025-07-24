package com.Hariteja.BloggingApp.articles;
import com.Hariteja.BloggingApp.articles.dto.CreateArticleRequest;
import com.Hariteja.BloggingApp.articles.dto.UpdateArticleRequest;
import com.Hariteja.BloggingApp.users.UserRepository;
import com.Hariteja.BloggingApp.users.UserService;
import org.springframework.stereotype.Service;

@Service
public class ArticleService {

    final private ArticleRepository articleRepository;
    final private UserRepository userRepository;

    ArticleService(ArticleRepository articleRepository, UserRepository userRepository){
        this.articleRepository=articleRepository;
        this.userRepository=userRepository;
    }

    public Iterable<ArticleEntity> getAllArticles(){
        return articleRepository.findAll();
    }

    public ArticleEntity getArticleBySlug(String slug){
        var article= articleRepository.findBySlug(slug);
        if(article==null) throw new ArticleNotFoundException(slug);
        return article;
    }

    public ArticleEntity createArticleRequest(CreateArticleRequest a, Long authorId){
        var author= userRepository.findById(authorId).orElseThrow(() -> new UserService.UserNotFoundException(authorId) );
        return articleRepository.save(ArticleEntity.builder()
                        .title(a.getTitle())
                        .slug(a.getTitle().toLowerCase().replaceAll("\\s","-"))
                        .body(a.getBody())
                        .subtitle(a.getSubtitle())
                        .author(author)
                        .build());

    }
    public ArticleEntity updateArticleRequest(Long articleId, UpdateArticleRequest u){
        var article= articleRepository.findById(articleId).orElseThrow(() -> new ArticleNotFoundException(articleId));

        if(u.getTitle()!=null){
            article.setTitle(u.getTitle());
            article.setSlug(u.getTitle().toLowerCase().replaceAll("\\s","-"));
        }
        if(u.getBody()!=null) article.setBody(u.getBody());
        if(u.getSubtitle()!=null) article.setSubtitle(u.getSubtitle());

        return articleRepository.save(article);

    }


    static class ArticleNotFoundException extends IllegalArgumentException{
        public ArticleNotFoundException(String slug){
            super("Article"+ slug + "Not Found");
        }
        public ArticleNotFoundException(Long articleId){
            super("Article with Id" + articleId+"Not Found");
        }
    }

}

