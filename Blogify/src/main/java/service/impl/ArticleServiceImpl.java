package service.impl;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import entity.Article; // Ensure this import is present and only once
import model.ArticleDTO;
import model.ArticleModel;
import repository.impl.ArticleRepositoryImpl;
import service.IArticleService;

public class ArticleServiceImpl implements IArticleService {

    private static final Logger logger = LoggerFactory.getLogger(ArticleServiceImpl.class);
    private final ArticleRepositoryImpl articleRepositoryImpl = new ArticleRepositoryImpl();

    @Override
    public Boolean addNewArticle(Article article) {
        Article newArticle = this.articleRepositoryImpl.save(article);
        return newArticle.getId() != null;
    }

    @Override
    public List<ArticleDTO> getAllArticles(int page) {  
        int from = 0;
        int length = 5;

        if (page > 1) {
            from = length * (page - 1);
        }

        // Fetch articles from the repository (should return List<Article>)
        List<ArticleDTO> articles = this.articleRepositoryImpl.getAllArticles(from, length); 

        return articles;

      
    }

    @Override
    public Article findArticleById(Long id) {
        Optional<Article> article = this.articleRepositoryImpl.getArticleById(id);
        return article.orElse(null); // Using Optional's method for cleaner code
    }

    @Override
    public Boolean updateArticle(Article article) {
        return this.articleRepositoryImpl.updateArticle(article);
    }

    @Override
    public ArticleModel getArticleById(Long id) {
        Article article = articleRepositoryImpl.readById(id);
        ArticleModel articleModel = new ArticleModel();

        if (article != null) {
            articleModel.setArticle(article);
            articleModel.setSuccessMessage("Article Retrieved Successfully");
        } else {
            articleModel.setErrorMessage("There is an error on article retrieving operation");
        }

        return articleModel;
    }

    @Override
    public int count() {
        return this.articleRepositoryImpl.countArticles();
    }

    @Override
    public boolean delete(Long id) {
      return this.articleRepositoryImpl.deleteArticle(id);
    }

   
}
