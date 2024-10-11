package service.impl;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import entity.User;
import model.UserModel;
import repository.impl.UserRepositoryImpl;
import service.UserService;


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
    public void updateUser(User u) {
        User user = userRepository.findUserById(u.getId());
        if (user != null) {
            userRepository.updateUser(u);
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
