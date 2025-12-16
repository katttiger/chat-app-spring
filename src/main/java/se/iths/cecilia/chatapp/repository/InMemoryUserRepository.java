package se.iths.cecilia.chatapp.repository;

import org.springframework.stereotype.Repository;
import se.iths.cecilia.chatapp.model.User;

import java.util.ArrayList;
import java.util.List;

@Repository
public class InMemoryUserRepository implements UserRepository {

    private final List<User> users = new ArrayList<>();
    private Long idCounter = 1L;

    @Override
    public User save(User user) {
        if (user.getId() == null) {
            user.setId(idCounter++);
        }
        users.add(user);
        return user;
    }

    @Override
    //TODO: I ditt nya UserRepository - implementera JOIN FETCH för denna metod
    public User findByUsernameAndPassword(String username, String password) {
        return users.stream()
                .filter(u -> u.getUsername().equals(username)
                        && u.getPassword().equals(password))
                .findFirst()
                .orElse(null);
    }



    @Override
    public List<User> findAll() {
        return users;
    }
}

