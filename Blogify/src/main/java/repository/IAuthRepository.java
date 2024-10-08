package repository;

import java.sql.SQLException;

import entity.User;

public interface IAuthRepository {
    public User login(String email , String password) throws SQLException;
    public User register(User user) throws SQLException;   
}
