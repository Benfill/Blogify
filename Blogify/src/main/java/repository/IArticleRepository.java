package repository;

import entity.Article;

public interface IArticleRepository {
	Article readById(long id);
}
