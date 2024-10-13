package repository.impl;

import java.util.List;
import java.util.Optional;
import javax.persistence.PersistenceException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import entity.User;
import repository.UserRepository;
import utils.HibernateUtil;

public class UserRepositoryImpl implements UserRepository {


    private static final Logger logger = LoggerFactory.getLogger(UserRepositoryImpl.class);

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
    public List<User> getAllUsers() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        List<User> users = null;

        try {
            transaction = session.beginTransaction();
            String hql = "FROM User";
            Query<User> query = session.createQuery(hql, User.class);
            users = query.getResultList();
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            logger.error("An error occurred while fetching all users", e);
        } finally {
            session.close();
        }

        return users;
    }

    @Override
    public User findUserById(Long id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        User user = null;

        try {
            transaction = session.beginTransaction();
            user = session.get(User.class, id);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            logger.error("An error occurred while finding user by ID: {}", id, e);
        } finally {
            session.close();
        }

        return user;
    }

    @Override
    public void updateUser(User user) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();
            User u = session.get(User.class, user.getId());
            u.setFirstName(user.getFirstName());
            u.setSecond_name(user.getSecondName());
            u.setEmail(user.getEmail());
            u.setPassword(user.getPassword());
            u.setBirthDate(user.getBirthDate());
            u.setRole(user.getRole());

            session.update(u);
            transaction.commit();
            logger.info("User updated: {}", user.getEmail());
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            logger.error("An error occurred while updating user", e);
        } finally {
            session.close();
        }
    }

    @Override
    public void removeUser(Long id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();
            User user = session.get(User.class, id);
            if (user != null) {
                session.delete(user);
                logger.info("User with id {} removed", id);
            } else {
                logger.info("User with id {} not found.", id);
            }
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            logger.error("An error occurred while removing user with id {}", id, e);
        } finally {
            session.close();
        }
    }
}
