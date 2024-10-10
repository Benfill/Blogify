package repository.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import entity.Comment;
import enums.CommentStatus;
import repository.ICommentRepository;

public class CommentRepositoryImpl implements ICommentRepository {
	private static final Logger log = Logger.getLogger(CommentRepositoryImpl.class.getName());
	private SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();

	@Override
	public List<Comment> readAll(int offset) {
		Session session = null;
		try {
			session = sessionFactory.openSession();
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

		Session session = sessionFactory.openSession();
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
	public void update(Comment comment) {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(Comment comment) {
		// TODO Auto-generated method stub

	}

	@Override
	public void changeStatus(int commentId, String newStatus) throws Exception {
		Session session = null;
		Transaction transaction = null;

		try {
			session = sessionFactory.openSession();
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
			session = sessionFactory.openSession();
			Query<Comment> query = session.createQuery("FROM Comment WHERE id = :id", Comment.class);
			query.setParameter("id", id);
			return query.uniqueResultOptional();
		} catch (Exception e) {
			e.printStackTrace();
			return Optional.empty();
		}
	}

}
