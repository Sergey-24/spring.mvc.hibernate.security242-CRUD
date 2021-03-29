package web.dao;

import org.springframework.stereotype.Repository;
import web.entity.Role;
import web.entity.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.HashSet;
import java.util.List;


@Repository
public class RoleDaoImpl implements RoleDao{

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public List<Role> getRoles(String role) {
        return entityManager.createQuery("select r from Role r WHERE r.role = :role", Role.class)
                .setParameter("role", role)
                .getResultList();
    }

    @Override
    public List<Role> findAllRoles() {
        return entityManager.createQuery("select distinct u from Role u join fetch u.users")
                .getResultList();
    }

    @Override
    public void saveRole(Role role) {
        entityManager.persist(role);
    }

    @Override
    public Role findRoleByName(String roleName) {
        return entityManager
                .createQuery("select u from Role u join fetch u.users where u.role = :rolename", Role.class)
                .setParameter("rolename", roleName)
                .getSingleResult();
    }
}
