package se.iths.cecilia.chatapp.dao;

import se.iths.cecilia.chatapp.model.Message;

import java.util.List;

public interface MessageDAO {
    void saveMessage(Message message);

    List<Message> getMessagesByUserId(int userId);
}
