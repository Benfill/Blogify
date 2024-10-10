package service;


import entity.Article;
import java.util.List;
import model.ArticleModel;
public interface IArticleService {
    public Boolean addNewArticle(Article article);
    public List<Article> allArticles();
    public Article findArticleById(Long id);
    public Boolean updateArticle(Article article);

	ArticleModel getArticleById(Long id);
}
