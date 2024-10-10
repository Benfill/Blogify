package service.impl;

import entity.User;
import repository.UserRepository;
import repository.impl.UserRepositoryImpl;
import service.UserService;

import java.util.List;
import java.util.Optional;

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
		userRepository.updateUser(user);
	}

	@Override
	public void updateUser(User u) {
		User user = userRepository.findUserById(u.getId());
		if (user != null) {
			userRepository.updateUser(u);
		}
	}

	@Override
	public void deleteUser(Long id) {
		User user = userRepository.findUserById(id);
		if (user != null) {
			userRepository.removeUser(id);
		}
	}

	@Override
	public Boolean userAlreadyExist(String email) {
		Optional<User> optionalUser = this.userRepository.findUserByEmail(email);
		return optionalUser.isPresent();
	}
}
