package repository.impl;

import java.util.List;
import java.util.Optional;

import entity.Article;
import model.ArticleDTO;

public interface IArticleRepository {
    public Article save(Article article);
    public List<ArticleDTO> getAllArticles(int from , int length);
    public Optional<Article> getArticleById(Long id);
    public Boolean updateArticle(Article article);
    public Article readById(long id);
    public int countArticles();
}
