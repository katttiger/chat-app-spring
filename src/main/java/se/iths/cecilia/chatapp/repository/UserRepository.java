package se.iths.cecilia.chatapp.repository;


import se.iths.cecilia.chatapp.model.User;
import java.util.List;

public interface UserRepository {
    User save(User user);
    User findByUsernameAndPassword(String username, String password);
    List<User> findAll();
}

