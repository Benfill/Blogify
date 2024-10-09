package repository.impl;

import java.sql.Connection;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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

	}

	@Override
	public void updateUser(User user) {
		// TODO Auto-generated method stub

	}

	@Override
	public void removeUser(int id) {
		// TODO Auto-generated method stub

	}

}
