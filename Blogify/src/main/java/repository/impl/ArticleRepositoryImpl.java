package repository.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import entity.Article;
import utils.HibernateUtil;

public class ArticleRepositoryImpl implements IArticleRepository {

    private static final Logger logger = LoggerFactory.getLogger(ArticleRepositoryImpl.class);

    @Override
    public Article save(Article article) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        try{
            
            transaction = session.beginTransaction();

            session.save(article);
            transaction.commit();
    
        } catch (Exception e) {
        if (transaction != null) {
            transaction.rollback();
        }
            logger.error("Could not register user", e);
        } finally {
            session.close(); 
        }

        return article;

    }

    @Override
    public List<Article> getAllArticles() {
       List<Article> articles = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;

         try{
            transaction = session.beginTransaction();
            String sql = "SELECT * FROM articles ;";
            Query<Article> query = session.createNativeQuery(sql, Article.class);
            articles = query.getResultList();

        } catch (Exception e) {
       
            logger.error("Could fetch articles", e);
        } finally {
            session.close(); 
        } 

        return articles;
    }
    
}
