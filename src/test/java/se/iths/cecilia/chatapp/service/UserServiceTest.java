package se.iths.cecilia.chatapp.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import se.iths.cecilia.chatapp.model.User;
import se.iths.cecilia.chatapp.repository.UserRepository;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @InjectMocks
    private UserService userService;

    @Mock
    private UserRepository userRepository;

    private User mockUser;


    @BeforeEach
    void setUp() {
        mockUser = new User();
        mockUser.setPassword("passwordTest");
        mockUser.setUsername("userTest");
    }

    @Test
    @DisplayName("Login succeeds. User exists in the database")
    void loginFindsAnExistingUser() {
        Mockito.when(userService.login(mockUser.getUsername(), mockUser.getPassword())).thenReturn(mockUser);
        User result = userService.login(mockUser.getUsername(), mockUser.getPassword());
        Assertions.assertEquals(mockUser, result);
    }

    @Test
    @DisplayName("When logging in, the findByUserNameAndPassword-method is called")
    void loginIsCalled() {
        userService.login(mockUser.getUsername(), mockUser.getPassword());
        Mockito.verify(userRepository).findByUserNameAndPassword(mockUser.getUsername(), mockUser.getPassword());
    }

    @Test
    @DisplayName("Login returns null as user entered does not exist")
    void userFailsDueToUserNotExisting() {
        Mockito.when(userService.login("faux_name", "faux_password"))
                .thenReturn(null);

        User result = userService.login("faux_name", "faux_password");

        Assertions.assertNull(result);

    }

    @Test
    @DisplayName("Successful registration.")
    void registerOfUserSucceeds() {
        Mockito.when(userService.register(mockUser)).thenReturn(mockUser);
        Assertions.assertEquals(userService.register(mockUser), mockUser);
    }

    @Test
    @DisplayName("When user is registered, the save method is called")
    void repositoryRegistryMethodIsCalled() {
        userService.register(mockUser);
        Mockito.verify(userRepository).save(mockUser);
    }
}