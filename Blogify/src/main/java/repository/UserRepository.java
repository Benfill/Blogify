package repository;

import entity.User;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface UserRepository {
     User findUserById(Long id);
     List<User> getAllUsers();
     void updateUser(User user);
     void removeUser(Long id);
     Optional<User> findUserByEmail(String email);
}