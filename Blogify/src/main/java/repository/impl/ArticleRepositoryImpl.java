package repository.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import entity.Article;
import repository.IArticleRepository;

public class ArticleRepositoryImpl implements IArticleRepository {
	private SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();

	@Override
	public Article readById(long id) {
		Session session = sessionFactory.openSession();

		session.beginTransaction();
		Article article = session.get(Article.class, id);

		session.getTransaction().commit();
		session.close();

		return article;
	}

}
