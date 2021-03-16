package web.dao;

import org.springframework.security.core.userdetails.UserDetails;
import web.entity.User;

import java.util.List;

public interface UserDao {
    List<User> getAllUsers();

    void saveUser(User user, String password);

    void delete(Long id);

    User findUser(Long id);

    void update(User user, String password);

    User findByUsername(String name);
}
