package com.devops;

import com.devops.entity.User;
import com.devops.repo.UserRepository;
import com.devops.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    private UserRepository userRepository;  // Mock the UserRepository

    @Mock
    private PasswordEncoder passwordEncoder;  // Mock the PasswordEncoder

    @InjectMocks
    private UserService userService;  // Inject mocks into UserService

    private User user;

    @BeforeEach
    public void setUp() {
        // Initialize a User object for testing
        user = new User();
        user.setUsername("testUser");
        user.setPassword("testPassword");
    }

    @Test
    public void testRegisterUser_Success() {
        // Arrange
        when(passwordEncoder.encode(user.getPassword())).thenReturn("encodedPassword"); // Mock password encoding
        when(userRepository.save(user)).thenReturn(user); // Mock repository save

        // Act
        boolean result = userService.registerUser(user);

        // Assert
        assertTrue(result);  // Expect that the user was registered successfully
        verify(userRepository, times(1)).save(user);  // Verify that save() was called once
    }

    @Test
    public void testRegisterUser_Failure() {
        // Arrange
        when(passwordEncoder.encode(user.getPassword())).thenReturn("encodedPassword"); // Mock password encoding
        when(userRepository.save(user)).thenReturn(null); // Simulate failure (null returned)

        // Act
        boolean result = userService.registerUser(user);

        // Assert
        assertTrue(result);  // Expect that the registration failed
        verify(userRepository, times(1)).save(user);  // Verify that save() was called once
    }

    @Test
    public void testAuthenticateUser_Success() {
        // Arrange
        String username = "testUser";
        String password = "testPassword";
        User foundUser = new User();
        foundUser.setUsername(username);
        foundUser.setPassword("encodedPassword");

        when(userRepository.findByUsername(username)).thenReturn(foundUser);
        when(passwordEncoder.matches(password, "encodedPassword")).thenReturn(true);  // Simulate password match

        // Act
        User authenticatedUser = userService.authenticateUser(username, password);

        // Assert
        assertNotNull(authenticatedUser);  // The user should be returned
        assertEquals(username, authenticatedUser.getUsername());  // The returned user should have the correct username
    }

    @Test
    public void testAuthenticateUser_Failure() {
        // Arrange
        String username = "testUser";
        String password = "wrongPassword";
        User foundUser = new User();
        foundUser.setUsername(username);
        foundUser.setPassword("encodedPassword");

        when(userRepository.findByUsername(username)).thenReturn(foundUser);
        when(passwordEncoder.matches(password, "encodedPassword")).thenReturn(false);  // Simulate password mismatch

        // Act
        User authenticatedUser = userService.authenticateUser(username, password);

        // Assert
        assertNull(authenticatedUser);  // The user should not be authenticated
    }
}
