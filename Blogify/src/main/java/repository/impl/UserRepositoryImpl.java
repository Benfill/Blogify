package repository.impl;

<<<<<<< HEAD
import entity.User;
import enums.UserRole;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import repository.UserRepository;
import util.DatabaseConnection;

import javax.persistence.EntityManager;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserRepositoryImpl implements UserRepository {
    private EntityManager entityManager;
    private final Connection cn;
    private final Logger log;

    public UserRepositoryImpl(EntityManager emf) {
        entityManager = emf;
        cn = DatabaseConnection.getConnection();
        log = LoggerFactory.getLogger(this.getClass());
    }

    @Override
    public User findUserById(int id) {
        String q = "select * from users where id = ?";
        try {
            PreparedStatement ps = cn.prepareStatement(q);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                User user = new User(rs.getString("first_name"), rs.getString("last_name"), rs.getString("email"), rs.getDate("birth_date").toLocalDate(), UserRole.valueOf(rs.getString("role")));
                user.setId(rs.getInt("id"));
                return user;
            }
        } catch (SQLException e){
            log.error("Failed to find user by id: {}", id, e);
        }
        return null;
    }

    @Override
    public User findUserByEmail(String email) {
        String q = "select u from users u where u.email = ?";
        try {
            PreparedStatement ps = cn.prepareStatement(q);
            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                User user = new User(rs.getString("first_name"), rs.getString("last_name"), rs.getString("email"), rs.getDate("birth_date").toLocalDate(), UserRole.valueOf(rs.getString("role")));
                user.setId(rs.getInt("id"));
                return user;
            }
        } catch (SQLException e){
            log.error("Failed to find user by email: {}", email, e);
        }
        return null;
    }

    @Override
    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        String q = "select * from users";
        try (Statement st = cn.createStatement()) {
            ResultSet rs = st.executeQuery(q);
            while (rs.next()) {
                User user = new User(rs.getString("first_name"), rs.getString("last_name"), rs.getString("email"), rs.getDate("birth_date").toLocalDate(), UserRole.valueOf(rs.getString("role")));
                user.setId(rs.getInt("id"));
                users.add(user);
            }
        } catch (SQLException e) {
            log.error("Failed fetching all users", e);
        }
        return users;
    }

    @Override
    public void addUser(User user) {
        String q = "insert into users (first_name, last_name, email, birth_date, role) values (?, ?, ?, ?, ?)";
        try {
            PreparedStatement ps = cn.prepareStatement(q);
            ps.setString(1, user.getFirstName());
            ps.setString(2, user.getLastName());
            ps.setString(3, user.getEmail());
            ps.setDate(4, Date.valueOf(user.getBirthDate()));
            ps.setString(5, user.getRole().name());
            ps.executeUpdate();
        } catch (SQLException e){
            log.error("Failed to create user.", e);
        }

    }

    @Override
    public void updateUser(User user) {
        String q = "update users set first_name = ?, last_name = ?, email = ?, birth_date = ?, role = ? where id = ?";
        try (PreparedStatement ps = cn.prepareStatement(q)){
            ps.setString(1, user.getFirstName());
            ps.setString(2, user.getLastName());
            ps.setString(3, user.getEmail());
            ps.setDate(4, Date.valueOf(user.getBirthDate()));
            ps.setString(5, user.getRole().name());
            ps.setInt(6, user.getId());
        } catch (SQLException e){
            log.error("Failed to update user.", e);
        }
    }

    @Override
    public void removeUser(int id){
        String q = "delete from users where id = ?";
        try (PreparedStatement stmt = cn.prepareStatement(q)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e){
            log.error("Failed to delete user by id: {}", id, e);
        }
    }
=======
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

  
    
>>>>>>> 1b223145b5564b98d891f2b3645ee50a386d37ec
}
