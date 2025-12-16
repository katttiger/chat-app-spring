package se.iths.cecilia.chatapp.repository;

import org.springframework.stereotype.Repository;
import se.iths.cecilia.chatapp.model.Message;

import java.util.ArrayList;
import java.util.List;

@Repository
public class InMemoryMessageRepository implements MessageRepository {

    private final List<Message> messages = new ArrayList<>();
    private Long idCounter = 1L;

    @Override
    public Message save(Message message) {
        message.setId(idCounter++);
        messages.add(message);
        return message;
    }

    @Override
    public List<Message> findByUserId(Long userId) {
        return messages.stream()
                .filter(m -> m.getUser().getId() == userId)
                .toList();
    }
}

