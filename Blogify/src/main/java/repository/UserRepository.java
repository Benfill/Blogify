package repository;

import entity.User;

import java.sql.SQLException;
import java.util.List;

public interface UserRepository {
    User findUserById(int id);
    User findUserByEmail(String email);
    List<User> getAllUsers();
    void addUser(User user);
    void editUser(User user);
    void removeUser(int id);
}
