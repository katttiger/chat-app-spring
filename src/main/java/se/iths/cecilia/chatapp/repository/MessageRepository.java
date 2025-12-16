package se.iths.cecilia.chatapp.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import se.iths.cecilia.chatapp.model.Message;
import se.iths.cecilia.chatapp.model.User;

import java.util.List;


public interface MessageRepository extends JpaRepository<Message, Long> {
    Long user(User user);

    List<Message> findByUserId(Long userId);
}


