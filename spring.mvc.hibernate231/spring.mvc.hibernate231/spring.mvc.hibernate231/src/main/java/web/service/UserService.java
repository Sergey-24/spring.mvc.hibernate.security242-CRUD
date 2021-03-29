package web.service;


import web.entity.User;
import java.util.List;


public interface UserService {
    List<User> getAllUsers();

    void saveUser(User user);

    void deleteUserById(Long id);

    User findUserById(Long id);

    void updateUser(User user, String newpassword);

    User findUserByUsername(String username);

}
