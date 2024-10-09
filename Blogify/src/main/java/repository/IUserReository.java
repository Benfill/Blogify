package repository;

import java.util.Optional;

import entity.User;

public interface IUserReository {
    public Optional<User> findUserByEmail(String email);
    public User getUserByEmail(String email);
}
