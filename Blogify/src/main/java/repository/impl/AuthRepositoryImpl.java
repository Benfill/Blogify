package repository.impl;

import java.sql.SQLException;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import entity.User;
import repository.IAuthRepository;
import utils.HibernateUtil;

public class AuthRepositoryImpl implements IAuthRepository {

    private static final Logger logger = LoggerFactory.getLogger(AuthRepositoryImpl.class);

    @Override
    public User login(String email, String password) throws SQLException {
        throw new UnsupportedOperationException("Unimplemented method 'login'");
        

    }

    @Override
    public User register(User user) throws SQLException {

        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        try{
            transaction = session.beginTransaction();

            session.save(user);
            transaction.commit();
    
            return user;
        } catch (Exception e) {
        if (transaction != null) {
            transaction.rollback();
        }
            logger.error("Could not register user", e);
            throw new SQLException("Could not register user", e);
        } finally {
            session.close(); 
        }

      
        
    }
    
}
