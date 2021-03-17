package web.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import web.entity.Role;
import web.entity.User;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class UserDaoImpl implements UserDao {

    @PersistenceContext
    EntityManager entityManager;

    private RoleDao roleDao;

    @Autowired
    public void setRoleDao(RoleDao roleDao) {
        this.roleDao = roleDao;
    }

    @Override
    public List<User> getAllUsers() {
        List<User> allUsers = entityManager.createQuery("from User"
                , User.class).getResultList();

        return allUsers;
    }

    @Override
    public void saveUserByUser(User user) {
        for (Role userRole : user.getRoles()) {
            for (Role role : roleDao.findAllRoles()) {
                if (role.getAuthority().equals(userRole.getAuthority())) {
                    userRole.setId(role.getId());
                }
            }
        }
        entityManager.persist(user);
    }

    @Override
    public void deleteUserById(Long id) {
        User user = entityManager.find(User.class, id);
        entityManager.remove(user);
    }

    @Override
    public User findUserById(Long id) {
        return entityManager.find(User.class, id);
    }

    @Override
    public void updateUserByUser(User user) {
        for (Role userRole : user.getRoles()) {
            for (Role role : roleDao.findAllRoles()) {
                if (role.getAuthority().equals(userRole.getAuthority())) {
                    userRole.setId(role.getId());
                }
            }
        }
        entityManager.merge(user);
    }

    @Override
    public User findUserByUsername(String name) {
        User user = entityManager.createQuery("SELECT u from User u WHERE u.firstName = :name", User.class).
                setParameter("name", name).getSingleResult();
        return user;

    }
}
