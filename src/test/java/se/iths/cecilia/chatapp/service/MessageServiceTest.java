package se.iths.cecilia.chatapp.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class MessageServiceTest {

    @InjectMocks
    private final MessageService messageServiceMock;

    MessageServiceTest(MessageService messageServiceMock) {
        this.messageServiceMock = messageServiceMock;
    }

    @BeforeEach
    void setup() {
    }

    @Test
    void save() {
    }

    @Test
    void getMessages() {
    }
}