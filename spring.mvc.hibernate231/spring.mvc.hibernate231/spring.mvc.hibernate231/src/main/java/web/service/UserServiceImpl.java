package web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import web.dao.UserDao;
import web.entity.Role;
import web.entity.User;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private UserDao userDao;

    @Autowired
    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    @Transactional
    public List<User> getAllUsers() {
        return userDao.getAllUsers();
    }

    @Override
    @Transactional
    public void saveUser(User user, String password) {
        userDao.saveUser(user, password);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        userDao.delete(id);
    }

    @Override
    @Transactional
    public User findUser(Long id) {
        return userDao.findUser(id);
    }

    @Override
    @Transactional
    public void update(User user, String password) {
        userDao.update(user, password);
    }

    @Override
    @Transactional
    public User findByUsername(String username) {
        return userDao.findByUsername(username);
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userDao.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException(user.getUsername() + " not found");
        }
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword()
                , convRoles(user.getRoles()));
    }

    private Collection<? extends GrantedAuthority> convRoles(Collection<Role> roles) {
        return roles.stream().map(x -> new SimpleGrantedAuthority(x.getUsername())).collect(Collectors.toList());
    }
}

