package service;

import java.util.List;

import entity.Article;

public interface IArticleService {
    public Boolean addNewArticle(Article article);
    public List<Article> allArticles();
}
