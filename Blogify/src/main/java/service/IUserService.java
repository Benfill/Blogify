package service;

import entity.User;
import java.util.*;

public interface IUserService {
     User findUserById(Long id);
     List<User> getAllUsers();
     void updateUser(User user);
     void removeUser(Long id);
     Optional<User> findUserByEmail(String email);
    
}
