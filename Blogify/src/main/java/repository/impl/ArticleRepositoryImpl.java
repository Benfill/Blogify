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

		String hql = "SELECT a FROM Article a LEFT JOIN FETCH a.comments c LEFT JOIN FETCH c.user "
				+ "WHERE a.id = :id";

		Article article = session.createQuery(hql, Article.class).setParameter("id", id).uniqueResult();

		session.getTransaction().commit();
		session.close();

		return article;
	}

}
