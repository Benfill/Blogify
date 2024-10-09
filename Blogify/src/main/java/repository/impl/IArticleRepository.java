package repository.impl;

import java.util.List;

import entity.Article;

public interface IArticleRepository {
    public Article save(Article article);
    public List<Article> getAllArticles();
}
