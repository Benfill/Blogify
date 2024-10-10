package repository.impl;

import java.util.List;
import java.util.Optional;

import entity.Article;

public interface IArticleRepository {
    public Article save(Article article);
    public List<Article> getAllArticles();
    public Optional<Article> getArticleById(Long id);
    public Boolean updateArticle(Article article);
}
