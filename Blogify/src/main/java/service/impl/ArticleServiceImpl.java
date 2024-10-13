package service.impl;

import java.util.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import entity.Article;
import entity.Article;
import model.ArticleDTO;
import model.ArticleModel;
import repository.impl.ArticleRepositoryImpl;
import service.IArticleService;

public class ArticleServiceImpl implements IArticleService {

    private static final Logger logger = LoggerFactory.getLogger(ArticleServiceImpl.class);
    private  ArticleRepositoryImpl articleRepositoryImpl ;

    public ArticleServiceImpl(ArticleRepositoryImpl articleRepositoryImpl){
        this.articleRepositoryImpl = articleRepositoryImpl;
    }

    @Override
    public Boolean addNewArticle(Article article) {
        Article newArticle = this.articleRepositoryImpl.save(article);
        return newArticle.getId()!=null;
    }

  

    @Override
    public Article findArticleById(Long id) {
        Optional<Article> article = this.articleRepositoryImpl.getArticleById(id);

        if (article.isPresent()) {
            return article.get(); 
        } else {
            return null;
        }
    }

    @Override
    public Boolean updateArticle(Article article) {
       return this.articleRepositoryImpl.updateArticle(article);
    }
    

	@Override
	public ArticleModel getArticleById(Long id) {
		
		Article article = this.articleRepositoryImpl.readById(id);
		ArticleModel articleModel = new ArticleModel();

		if (article != null) {
			articleModel.setArticle(article);
			articleModel.setSuccessMessage("Article Retrieved Successfully");
		} else
			articleModel.setErrorMessage("There is an error on article retrieving operation");

		return articleModel;
	}

   
    public List<ArticleDTO> getAllArticles(int page) {  

        int from = 0;
        int length = 5;
        if (page > 1) {
            from = length * (page - 1);
        }
        List<ArticleDTO> articles = this.articleRepositoryImpl.getAllArticles(from, length); 
        return articles;
      
    }

       @Override
    public int count() {
        return this.articleRepositoryImpl.countArticles();
    }

    @Override
    public boolean deleteArticle(Long id) {
        return this.articleRepositoryImpl.deleteArticle(id);
    }

    

    

    

}
