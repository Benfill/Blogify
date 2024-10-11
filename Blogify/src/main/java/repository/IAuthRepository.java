package repository;

import java.sql.SQLException;

import entity.User;

public interface IAuthRepository {
    // public User validateUser(String email ) throws SQLException;
    public User register(User user) throws SQLException;   
}
