package repository.impl;

import java.util.List;
import java.util.Optional;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import entity.Article;
import model.ArticleDTO;
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
    public List<ArticleDTO> getAllArticles(int from,int length) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();

        String hql = "SELECT new model.ArticleDTO(a.id, a.articlePictureUrl, a.content, "
                + "a.creationDate, a.publishedDateTime, a.status, a.title, "
                + "u.id, u.firstName , u.secondName) " 
                + "FROM Article a LEFT JOIN a.user u"; 

        List<ArticleDTO> articles = session.createQuery(hql, ArticleDTO.class)
                                        .setFirstResult(from)
                                        .setMaxResults(length)
                                        .getResultList();

        session.getTransaction().commit();
        session.close();

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
  
    @Override
	public Article readById(long id) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();

		String hql = "SELECT a FROM Article a LEFT JOIN FETCH a.comments c LEFT JOIN FETCH c.user "
				+ "WHERE a.id = :id";
                


		Article article = session.createQuery(hql, Article.class).setParameter("id", id).uniqueResult();

		session.getTransaction().commit();
		session.close();

		return article;
	}

    @Override
    public int countArticles() {
		Session session = HibernateUtil.getSessionFactory().openSession();
        
    
        try {
            String hql = "SELECT COUNT(a) FROM Article a";
            Query<Long> query = session.createQuery(hql, Long.class);
            
            Long count = query.uniqueResult();
            
            return (count != null) ? count.intValue() : 0;
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public boolean deleteArticle(Long id) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            
            Article article = session.get(Article.class, id);
            
            if (article != null) {
                session.delete(article);
                transaction.commit();
                return true; 
            } else {
                logger.warn("Article not found for ID: " + id);
                return false; 
            }
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback(); 
            }
            logger.error("Error deleting article with ID: " + id, e);
            return false;
        }
    }
    
    
}
