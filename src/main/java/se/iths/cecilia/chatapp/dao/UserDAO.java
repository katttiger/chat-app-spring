package se.iths.cecilia.chatapp.dao;

import se.iths.cecilia.chatapp.model.User;

public interface UserDAO {
    User login(String username, String password);

    User register(User user);
}
