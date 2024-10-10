package service;


import java.util.Optional;

import entity.User;

public interface IAuthService {
    public Optional<User> login(String email ) ;
    public User register(User user);
}
