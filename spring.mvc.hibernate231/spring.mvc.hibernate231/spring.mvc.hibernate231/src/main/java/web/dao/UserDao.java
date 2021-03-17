package web.dao;

import web.entity.User;

import java.util.List;

public interface UserDao {
    List<User> getAllUsers();

    void saveUserByUser(User user);

    void deleteUserById(Long id);

    User findUserById(Long id);

    void updateUserByUser(User user);

    User findUserByUsername(String name);
}
