package service.impl;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import entity.Article;
import entity.Article;
import model.ArticleModel;
import repository.impl.ArticleRepositoryImpl;
import service.IArticleService;

public class ArticleServiceImpl implements IArticleService {

    private static final Logger logger = LoggerFactory.getLogger(ArticleServiceImpl.class);
    private final ArticleRepositoryImpl articleRepositoryImpl = new ArticleRepositoryImpl();

    @Override
    public Boolean addNewArticle(Article article) {
        Article newArticle = this.articleRepositoryImpl.save(article);
        return newArticle.getId()!=null;
    }

    @Override
    public List<Article> allArticles() {
        return this.articleRepositoryImpl.getAllArticles();
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
    
	private ArticleRepositoryImpl articleRepo = new ArticleRepositoryImpl();

	@Override
	public ArticleModel getArticleById(String id) {
		if (!id.matches("-?\\d+(\\.\\d+)?"))
			return null;
		Article article = articleRepo.readById(Long.parseLong(id));
		ArticleModel articleModel = new ArticleModel();

		if (article != null) {
			articleModel.setArticle(article);
			articleModel.setSuccessMessage("Article Retrieved Successfully");
		} else
			articleModel.setErroMessage("There is an error on article retrieving operation");

		return articleModel;
	}

}
