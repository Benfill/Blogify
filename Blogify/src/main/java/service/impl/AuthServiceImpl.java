package service.impl;

import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import entity.User;
import repository.impl.AuthRepositoryImpl;
import service.IAuthService;

public class AuthServiceImpl implements IAuthService {

    private static final Logger logger = LoggerFactory.getLogger(AuthServiceImpl.class);

    private final AuthRepositoryImpl authRepositoryImpl = new AuthRepositoryImpl();

    @Override
    public User login(String email, String password) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'login'");
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
