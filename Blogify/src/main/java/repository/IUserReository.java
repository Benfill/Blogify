package repository;

import java.util.Optional;

import entity.User;

public interface IUserReository {
    public Optional<User> findUserByEmail(String email);
}
