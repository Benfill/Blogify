package service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.time.LocalDate;
import java.util.Optional;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import entity.User;
import enums.UserRole;
import model.UserModel;
import repository.impl.UserRepositoryImpl;
import service.impl.UserServiceImpl;

class UserServiceTest {

    @Mock
    private UserRepositoryImpl userRepository;

    @InjectMocks
    private UserServiceImpl userService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllUsers() {
        List<User> mockUsers = Arrays.asList(
                new User("user one", "one", "user_one@example.com", "user_one_password", null, UserRole.EDITOR),
                new User("user two", "two", "user_two@example.com", "user_two_password", null, UserRole.CONTRIBUTOR)
        );
        when(userRepository.getAllUsers()).thenReturn(mockUsers);

        List<User> result = userService.getAllUsers();

        assertEquals(2, result.size());
        verify(userRepository).getAllUsers();
    }

    @Test
    void testGetUserById() {
        User mockUser = new User("user", "one", "user_one@example.com", "password", null, UserRole.EDITOR);
        when(userRepository.findUserById(1L)).thenReturn(mockUser);

        User result = userService.getUserById(1L);

        assertNotNull(result);
        assertEquals("user_one@example.com", result.getEmail());
        verify(userRepository).findUserById(1L);
    }

    @Test
    void testGetUserByEmail() {
        User mockUser = new User("user", "one", "user_one@example.com", "password", null, UserRole.EDITOR);
        when(userRepository.findUserByEmail("user_one@example.com")).thenReturn(Optional.of(mockUser));

        User result = userService.getUserByEmail("user_one@example.com");

        assertNotNull(result);
        assertEquals("user_one@example.com", result.getEmail());
        verify(userRepository).findUserByEmail("user_one@example.com");
    }


    @Test
    void testUpdateUser() {
        User existingUser = new User(1L, "user", "one", "user_one@example.com", "password", LocalDate.now(), UserRole.CONTRIBUTOR);
        User updatedUser = new User(1L, "usr", "two", "user_two@example.com", "new_password", LocalDate.now(), UserRole.EDITOR);

        when(userRepository.findUserById(1L)).thenReturn(existingUser);
        UserModel result = userService.updateUser(updatedUser);

        assertEquals("User successfully updated.", result.getSuccess());
        verify(userRepository).updateUser(updatedUser);
    }

    @Test
    void testDeleteUser() {
        User mockUser = new User("user", "one", "user_one@example.com", "password", null, UserRole.EDITOR);
        when(userRepository.findUserById(1L)).thenReturn(mockUser);

        UserModel result = userService.deleteUser(1L);

        assertEquals("User deleted Successfully", result.getSuccess());
        verify(userRepository).removeUser(1L);
    }

    @Test
    void testUserAlreadyExist() {
        User mockUser = new User("user", "one", "user_one@example.com", "password", null, UserRole.EDITOR);
        when(userRepository.findUserByEmail("user_one@example.com")).thenReturn(Optional.of(mockUser));

        Boolean result = userService.userAlreadyExist("user_one@example.com");

        assertTrue(result);
        verify(userRepository).findUserByEmail("user_one@example.com");
    }
}
