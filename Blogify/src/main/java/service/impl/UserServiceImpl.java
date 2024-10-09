package service.impl;

import java.util.Optional;

import entity.User;
import repository.impl.UserRepositoryImpl;
import service.IUserService;

public class UserServiceImpl implements IUserService {

    private final UserRepositoryImpl userRepositoryImpl = new UserRepositoryImpl(); 

   

    @Override
    public Boolean userAlreadyExist(String email) {
       Optional<User> optionalUser = this.userRepositoryImpl.findUserByEmail(email);

        return optionalUser.isPresent();
    }

    @Override
    public User getUserByEmail(String email) {
		return this.userRepositoryImpl.getUserByEmail(email);
	}
    
    
}
