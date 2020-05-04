package com.example.main;

import java.util.ArrayList;
import java.util.List;

public class Teacher {
    private String email;
    private List<Article> articles;

    public Teacher(String email) {
        this.email = email;
        articles = new ArrayList<>();
    }

    public Teacher() {
        articles = new ArrayList<>();
    }
    public void addArticle(Article article){
        articles.add(article);
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Article> getArticles() {
        return articles;
    }

    public void setArticles(List<Article> articles) {
        this.articles = articles;
    }
}
