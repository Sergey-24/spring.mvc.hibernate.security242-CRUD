package web.dao;

import org.springframework.stereotype.Repository;
import web.entity.Role;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.HashSet;
import java.util.Set;

@Repository
public class RoleDaoImpl implements RoleDao{

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public Set<Role> getRoles(Set<String> role) {
        return new HashSet<>(entityManager.createQuery("SELECT r FROM Role r WHERE r.username in (:role)")
                .setParameter("role", role)
                .getResultList());
    }

    @Override
    public Set<Role> findAllRoles() {
        return new HashSet<>(entityManager.createQuery("SELECT r from Role r", Role.class)
                .getResultList());
    }

    @Override
    public void saveRole(Role role) {
        entityManager.persist(role);
    }
}
