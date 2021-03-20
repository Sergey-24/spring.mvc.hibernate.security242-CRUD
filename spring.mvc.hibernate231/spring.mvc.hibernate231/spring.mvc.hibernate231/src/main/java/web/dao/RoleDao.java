package web.dao;

import web.entity.Role;
import web.entity.User;

import java.util.Set;

public interface RoleDao {

    Role getRoles(String role);

    Set<Role> findAllRoles();

    void saveRole(Role role);
}
