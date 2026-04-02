package com.senim.backend.controller;

import com.senim.backend.model.Article;
import com.senim.backend.repository.ArticleRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/articles")
@CrossOrigin(origins = "*")
public class ArticleController {

    private final ArticleRepository articleRepository;

    public ArticleController(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    @GetMapping
    public List<Article> getAllArticles() {
        return articleRepository.findAll();
    }

    @PostMapping
    public Article createArticle(@RequestBody Article article) {
        return articleRepository.save(article);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Article> getArticleById(@PathVariable Long id) {
        return articleRepository.findById(id)
                .map(article -> ResponseEntity.ok().body(article))
                .orElse(ResponseEntity.notFound().build());
    }
}