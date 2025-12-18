package se.iths.cecilia.chatapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.iths.cecilia.chatapp.model.User;
import se.iths.cecilia.chatapp.repository.UserRepository;

@Service
public class UserService {

    private final UserRepository repo;

    @Autowired
    public UserService(UserRepository repo) {
        this.repo = repo;
    }

    public User login(String username, String password) {
        return repo.findByUserNameAndPassword(username, password);
    }

    public User register(User user) {
        return repo.save(user);
    }


}
