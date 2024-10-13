package service.impl;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import entity.User;
import enums.UserRole;
import model.UserModel;
import repository.impl.UserRepositoryImpl;
import service.UserService;
import utils.PasswordUtil;


public class UserServiceImpl implements UserService {

    private UserRepositoryImpl userRepository;

    public UserServiceImpl() {
        userRepository = new UserRepositoryImpl();
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.getAllUsers();
    }

    @Override
    public User getUserById(Long id) {
        return userRepository.findUserById(id);
    }

    @Override
    public User getUserByEmail(String email) {
        return userRepository.findUserByEmail(email).orElse(null);
    }

    @Override
    public void createUser(User user) {
        AuthServiceImpl authService = new AuthServiceImpl();
        authService.register(user);
    }

    @Override
    public UserModel updateUser(User u) {
        UserModel model = new UserModel();
        User user = userRepository.findUserById(u.getId());
        if (user == null) {
            model.setError("User not found.");
            return model;
        } else if ("ADMIN".equals(user.getRole().toString())) {
            u.setPassword(user.getPassword());
            u.setRole(UserRole.ADMIN);
            userRepository.updateUser(u);
            model.setSuccess("User successfully updated. (note: can't update Admin role or pwd, for safety)");
            return model;
        }  else {
            if (u.getPassword().isEmpty()) u.setPassword(user.getPassword());
            userRepository.updateUser(u);
            model.setSuccess("User successfully updated.");
            return model;
        }
    }

    @Override
    public UserModel deleteUser(Long id) {
        UserModel model = new UserModel();
        User user = userRepository.findUserById(id);
        if (user != null) {
            if ("ADMIN".equals(user.getRole().toString())) {
                model.setError("Can not delete Admin");
            } else {
                userRepository.removeUser(id);
                model.setSuccess("User deleted Successfully");
            }
        } else {
            model.setError("No user found.");
        }
        return model;
    }

    @Override
    public Boolean userAlreadyExist(String email) {
        Optional<User> optionalUser = this.userRepository.findUserByEmail(email);
        return optionalUser.isPresent();
    }


}
