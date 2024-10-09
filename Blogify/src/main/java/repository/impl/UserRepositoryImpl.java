package repository.impl;

import java.sql.Connection;
import java.util.List;
import java.util.Optional;

import org.hibernate.query.Query;
import org.hibernate.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.hibernate.Session;
import javax.persistence.EntityManager;
import entity.User;
import repository.UserRepository;
import utils.DatabaseConnection;
import utils.HibernateUtil;

public class UserRepositoryImpl implements UserRepository {
	private EntityManager entityManager;
	private final Connection cn;
	private final Logger log;

	public UserRepositoryImpl(EntityManager emf) {
		entityManager = emf;
		cn = DatabaseConnection.getConnection();
		log = LoggerFactory.getLogger(this.getClass());
	}

	public Optional<User> findUserByEmail(String email) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = null;
		Optional<User> useOptional = Optional.empty();

		try {
			transaction = session.beginTransaction();
			String sql = "SELECT * FROM users WHERE email = :email";
			Query<User> query = session.createNativeQuery(sql, User.class);
			query.setParameter("email", email);
			useOptional = query.uniqueResultOptional();

		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			logger.error("Could find user by email", e);
		} finally {
			session.close();
		}

		return useOptional;
	}

	@Override
	public User findUserById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<User> getAllUsers() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addUser(User user) {
		// TODO Auto-generated method stub

    @Override
    public Optional<User> findUserByEmail(String email) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        Optional<User> userOptional = Optional.empty();

        try {
            transaction = session.beginTransaction();
            String sql = "SELECT * FROM users WHERE email = :email";
            Query<User> query = session.createNativeQuery(sql, User.class);
            query.setParameter("email", email);
            userOptional = query.uniqueResultOptional();
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            logger.error("Could not find user by email", e);
        } finally {
            session.close(); 
        }

        return userOptional;
    }

    @Override
    public User getUserByEmail(String email) {
        Session session = null;
        User user = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();

            String q = "SELECT u FROM User u WHERE u.email = :email";
            Query<User> query = session.createQuery(q, User.class);
            query.setParameter("email", email);

            user = query.uniqueResult();
            session.getTransaction().commit();
        } catch (Exception e) {
            if (session != null) {
                session.getTransaction().rollback();
            }
            logger.error("Could not get user by email", e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return user;
    }
}
