package service.impl;

import entity.User;
import repository.UserRepository;
import repository.impl.UserRepositoryImpl;
import service.UserService;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public class UserServiceImpl implements UserService {
	private EntityManagerFactory emf;
	private UserRepositoryImpl userRepository;

	public void UserService() {
		emf = Persistence.createEntityManagerFactory("userPU");
		EntityManager em = emf.createEntityManager();
		userRepository = new UserRepositoryImpl(em);
	}

	public List<User> getAllUsers() {
		return userRepository.getAllUsers();
	}

	public User getUserById(int id) {
		return userRepository.findUserById(id);
	}

	public User getUserByEmail(String email) {
		return userRepository.findUserByEmail(email);
	}

	public void createUser(User user) {
//        User user = new User(name, email, password);
		userRepository.addUser(user);
	}

	public void updateUser(User u) {
		User user = userRepository.findUserById(u.getId());
		if (user != null) {
			userRepository.updateUser(user);
		}
	}

	public void deleteUser(int id) {
		User user = userRepository.findUserById(id);
		if (user != null) {
			userRepository.removeUser(id);
		}
	}

	@Override
	public Boolean userAlreadyExist(String email) {
		Optional<User> optionalUser = this.userRepositoryImpl.findUserByEmail(email);

		return optionalUser.isPresent();
	}
