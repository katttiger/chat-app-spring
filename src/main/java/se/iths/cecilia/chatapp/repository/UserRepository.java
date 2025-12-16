package se.iths.cecilia.chatapp.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import se.iths.cecilia.chatapp.model.User;

public interface UserRepository extends JpaRepository<User, Long> {

    @Query("SELECT user FROM User user " +
            "LEFT JOIN FETCH user.messages " +
            "WHERE user.userName=:username AND user.password=:password")
    User findByUserNameAndPassword(String username, String password);
}

