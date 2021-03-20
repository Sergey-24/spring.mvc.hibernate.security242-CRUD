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
    public Role getRoles(String role) {
        return entityManager.createQuery("select r from Role r join fetch r.users WHERE r.role = :role", Role.class)
                .setParameter("role", role)
                .getSingleResult();
    }

    @Override
    public Set<Role> findAllRoles() {
//        return new HashSet<>(entityManager.createQuery("SELECT r from Role r", Role.class)
//                .getResultList());
        return new HashSet<>(entityManager.createQuery("select distinct r from Role r join fetch r.users")
                .getResultList());
    }

    @Override
    public void saveRole(Role role) {
        entityManager.persist(role);
    }
}
