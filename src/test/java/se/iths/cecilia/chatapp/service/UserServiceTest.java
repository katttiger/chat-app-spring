package se.iths.cecilia.chatapp.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @InjectMocks
    private final UserService userServiceMock;

    UserServiceTest(UserService userServiceMock) {
        this.userServiceMock = userServiceMock;
    }

    @BeforeEach
    void setUp() {
    }

    @Test
    void login() {
    }

    @Test
    void register() {
    }
}