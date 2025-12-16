package se.iths.cecilia.chatapp.repository;



import se.iths.cecilia.chatapp.model.Message;

import java.util.List;


public interface MessageRepository {
    Message save(Message message);
    List<Message> findByUserId(Long userId);
}


