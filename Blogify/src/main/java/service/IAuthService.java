package service;


import entity.User;

public interface IAuthService {
    public User login(String email , String password) ;
    public User register(User user);
}
