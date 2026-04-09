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

    @GetMapping("/{id}")
    public ResponseEntity<Article> getArticleById(@PathVariable Long id) {
        return articleRepository.findById(id)
                .map(article -> ResponseEntity.ok().body(article))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Article createArticle(@RequestBody Article article) {
        return articleRepository.save(article);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Article> updateArticle(@PathVariable Long id, @RequestBody Article articleDetails) {
        return articleRepository.findById(id).map(article -> {
            article.setTitle(articleDetails.getTitle());
            article.setTitle_kz(articleDetails.getTitle_kz());
            article.setTitle_ru(articleDetails.getTitle_ru());
            article.setContent(articleDetails.getContent());
            article.setContent_kz(articleDetails.getContent_kz());
            article.setContent_ru(articleDetails.getContent_ru());
            article.setScamCategory(articleDetails.getScamCategory());
            article.setImageUrl(articleDetails.getImageUrl());
            return ResponseEntity.ok(articleRepository.save(article));
        }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteArticle(@PathVariable Long id) {
        return articleRepository.findById(id).map(article -> {
            articleRepository.delete(article);
            return ResponseEntity.ok().build();
        }).orElse(ResponseEntity.notFound().build());
    }
}