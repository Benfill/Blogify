package repository.impl;

import java.util.List;
import java.util.Optional;

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

    @Override
    public Optional<Article> getArticleById(Long id) {
        Transaction transaction = null;
        Article article = null;
        
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            article = session.get(Article.class, id); 
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            logger.error("Error fetching article by ID", e);
        }
        
        return Optional.ofNullable(article);
    }

    @Override
    public Boolean updateArticle(Article article) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
    
            Article existingArticle = session.get(Article.class, article.getId());
            
            if (existingArticle != null) {
                existingArticle.setTitle(article.getTitle());
                existingArticle.setContent(article.getContent());
                existingArticle.setStatus(article.getStatus());
                existingArticle.setPublishedDateTime(article.getPublishedDateTime());
    
                session.update(existingArticle);
                transaction.commit();
                return true;
            } else {
                logger.warn("Article not found for ID: " + article.getId());
                return false; 
            }
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback(); 
            }
            logger.error("Error updating article", e);
            return false; 
        }
    }
    
    
}
