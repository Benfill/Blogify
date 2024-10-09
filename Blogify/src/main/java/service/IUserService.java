package service;

import entity.User;

public interface IUserService {
    public Boolean userAlreadyExist(String email);
    public User getUserByEmail(String email);
    
}
