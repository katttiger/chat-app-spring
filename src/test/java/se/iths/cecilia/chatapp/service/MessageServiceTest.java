package se.iths.cecilia.chatapp.service;

import jdk.jfr.Description;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import se.iths.cecilia.chatapp.model.Message;
import se.iths.cecilia.chatapp.model.User;
import se.iths.cecilia.chatapp.repository.MessageRepository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@ExtendWith(MockitoExtension.class)
class MessageServiceTest {

    @InjectMocks
    private MessageService messageServiceMock;

    @Mock
    private MessageRepository messageRepository;

    User mockUser;
    Message mockMessage;
    List<Message> mockMessagesList = new ArrayList<>();


    @BeforeEach
    void setup() {
        mockUser = new User();
        mockUser.setId(1L);
        mockUser.setPassword("passwordTest");
        mockUser.setUsername("userTest");

        mockMessage = new Message();
        mockMessage.setId(2L);
        mockMessage.setText("Lorem ipsom dolor");
        mockMessage.setTimestamp(LocalDateTime.now());
        mockMessage.setUser(mockUser);
        mockMessagesList.add(mockMessage);

        mockMessage = new Message();
        mockMessage.setId(3L);
        mockMessage.setText("Lorem ipsom dolor");
        mockMessage.setTimestamp(LocalDateTime.now());
        mockMessage.setUser(mockUser);
        mockMessagesList.add(mockMessage);

        mockMessage = new Message();
        mockMessage.setId(4L);
        mockMessage.setText("Lorem ipsom dolor");
        mockMessage.setTimestamp(LocalDateTime.now());
        mockMessage.setUser(mockUser);
        mockMessagesList.add(mockMessage);


    }

    @Test
    @DisplayName("Method save is called when saving new message")
    void saveMethodIsCalled() {
        messageServiceMock.save(mockMessage);
        Mockito.verify(messageRepository).save(mockMessage);
    }

    @Test
    @Description("Message is retrieved from database")
    void getMessages() {
        Mockito.when(messageServiceMock.getMessages(mockUser.getId())).thenReturn(mockMessagesList);
        List<Message> messageList = messageServiceMock.getMessages(mockUser.getId());
        Assertions.assertEquals(mockMessagesList, messageList);
    }

    @Test
    @Description("Method findByUserId is called when retrieving messages.")
    void methodsFindUserByIdIsCalledWhenRetrievingMessages() {
        messageServiceMock.getMessages(1L);
        Mockito.verify(messageRepository).findByUserId(1L);
    }
}