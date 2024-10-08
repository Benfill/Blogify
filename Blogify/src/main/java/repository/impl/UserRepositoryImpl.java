package repository.impl;

import java.util.Optional;

import org.hibernate.query.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import entity.User;
import repository.IUserReository;
import utils.HibernateUtil;

public class UserRepositoryImpl implements IUserReository {

    private static final Logger logger = LoggerFactory.getLogger(UserRepositoryImpl.class);


    @Override
    public Optional<User> findUserByEmail(String email) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        Optional<User> useOptional = Optional.empty();

         try{
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

  
    
}
