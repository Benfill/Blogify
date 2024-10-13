package repository.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import entity.Comment;
import enums.CommentStatus;
import repository.ICommentRepository;
import utils.HibernateUtil;

public class CommentRepositoryImpl implements ICommentRepository {
	private static final Logger log = Logger.getLogger(CommentRepositoryImpl.class.getName());

	@Override
	public List<Comment> readAll(int offset) {
		try {
			Session session = HibernateUtil.getSessionFactory().openSession();
			Query<Comment> query = session.createQuery("FROM Comment", Comment.class);
			query.setFirstResult(offset);
			query.setMaxResults(5);
			return query.getResultList();
		} catch (Exception e) {
			// Log the error
			e.printStackTrace();
			return new ArrayList<>();
		}
	}

	@Override
	public void insert(Comment comment) throws Exception {

		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = null;

		try {
			transaction = session.beginTransaction();

			session.save(comment);

			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace(); // Add this line to print the full stack trace
			System.err.println("Error inserting comment: " + e.getMessage());
			log.warning("error: " + e.getMessage());
			throw new Exception("Something went wrong with Database");
		} finally {
			session.close();
		}
	}

	@Override
	public void update(Comment commentObj, String content) throws Exception {
		Session session = null;
		Transaction transaction = null;

		try {
			session = HibernateUtil.getSessionFactory().openSession();
			transaction = session.beginTransaction();

			Comment comment = session.get(Comment.class, commentObj.getId());
			if (comment != null) {
				comment.setContent(content);
				session.update(comment);
			}

			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			throw new Exception("Error updating comment status", e);
		} finally {
			if (session != null) {
				session.close();
			}
		}

	}

	@Override
	public void delete(Comment comment) throws Exception {
		Transaction transaction = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();

			Comment entity = session.get(Comment.class, comment.getId());

			if (entity != null) {
				session.delete(entity);
			}

			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			throw new Exception("error: " + e.getMessage());
		}
	}

	@Override
	public void changeStatus(int commentId, String newStatus) throws Exception {
		Session session = null;
		Transaction transaction = null;

		try {
			session = HibernateUtil.getSessionFactory().openSession();
			transaction = session.beginTransaction();

			Comment comment = session.get(Comment.class, commentId);
			if (comment != null) {
				comment.setCommentStatus(CommentStatus.valueOf(newStatus));
				session.update(comment);
			}

			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			throw new Exception("Error updating comment status", e);
		} finally {
			if (session != null) {
				session.close();
			}
		}
	}

	@Override
	public Optional<Comment> readById(int id) {
		Session session = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			Query<Comment> query = session.createQuery("FROM Comment WHERE id = :id", Comment.class);
			query.setParameter("id", id);
			return query.uniqueResultOptional();
		} catch (Exception e) {
			e.printStackTrace();
			return Optional.empty();
		}
	}

	@Override
	public long getCount() {
		try {
			Session session = HibernateUtil.getSessionFactory().openSession();
			Query<Long> query = session.createQuery("SELECT COUNT(*) FROM Comment", Long.class);
			return query.getSingleResult();
		} catch (Exception e) {
			// Log the error
			e.printStackTrace();
			return 0;
		}
	}

}
