package service.impl;

import java.sql.SQLException;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import entity.User;
import repository.impl.AuthRepositoryImpl;
import repository.impl.UserRepositoryImpl;
import service.IAuthService;

public class AuthServiceImpl implements IAuthService {

    private static final Logger logger = LoggerFactory.getLogger(AuthServiceImpl.class);

    private final AuthRepositoryImpl authRepositoryImpl = new AuthRepositoryImpl();
    private final UserRepositoryImpl userRepositoryImpl = new UserRepositoryImpl();


    @Override
    public Optional<User> login(String email) {
       return this.userRepositoryImpl.findUserByEmail(email);
       
    }

    @Override
    public User register(User user) {
        User inserted = null;
        try {
            inserted = this.authRepositoryImpl.register(user);
        } catch (Exception e) {  
            logger.error("Error in register service: register", e);
            return null;
        }
    
        return inserted;
    }

    
}
