package service;



import entity.Article;
import java.util.List;
import model.ArticleDTO;
import model.ArticleModel;


public interface IArticleService {
    public Boolean addNewArticle(Article article);
    // public List<Article> allArticles();
    public List<ArticleDTO> getAllArticles(int page,String filter);
    public Article findArticleById(Long id);
    public Boolean updateArticle(Article article);
    public int count();
	ArticleModel getArticleById(Long id);
    public boolean deleteArticle(Long id);
    public boolean like(Long articleId);
}