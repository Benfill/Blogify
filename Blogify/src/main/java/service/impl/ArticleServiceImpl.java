package service.impl;

import entity.Article;
import model.ArticleModel;
import repository.impl.ArticleRepositoryImpl;
import service.IArticleService;

public class ArticleServiceImpl implements IArticleService {

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
