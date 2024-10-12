package service;


import entity.Article;
import java.util.List;

import model.ArticleDTO;
import model.ArticleModel;
public interface IArticleService {
    public Boolean addNewArticle(Article article);
    public List<ArticleDTO> getAllArticles(int page);
    public Article findArticleById(Long id);
    public Boolean updateArticle(Article article);
    public int count();
	ArticleModel getArticleById(Long id);
    Boolean delete(Long id);
}
