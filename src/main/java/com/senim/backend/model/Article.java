package com.senim.backend.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "articles")
public class Article {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String title_kz;
    private String title_ru;

    @Column(columnDefinition = "TEXT")
    private String content;

    @Column(columnDefinition = "TEXT")
    private String content_kz;

    @Column(columnDefinition = "TEXT")
    private String content_ru;

    private String scamCategory;
    private String imageUrl;

    private LocalDateTime createdAt = LocalDateTime.now();

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public String getTitle_kz() { return title_kz; }
    public void setTitle_kz(String title_kz) { this.title_kz = title_kz; }
    public String getTitle_ru() { return title_ru; }
    public void setTitle_ru(String title_ru) { this.title_ru = title_ru; }

    public String getContent() { return content; }
    public void setContent(String content) { this.content = content; }
    public String getContent_kz() { return content_kz; }
    public void setContent_kz(String content_kz) { this.content_kz = content_kz; }
    public String getContent_ru() { return content_ru; }
    public void setContent_ru(String content_ru) { this.content_ru = content_ru; }

    public String getScamCategory() { return scamCategory; }
    public void setScamCategory(String scamCategory) { this.scamCategory = scamCategory; }

    public String getImageUrl() { return imageUrl; }
    public void setImageUrl(String imageUrl) { this.imageUrl = imageUrl; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
}