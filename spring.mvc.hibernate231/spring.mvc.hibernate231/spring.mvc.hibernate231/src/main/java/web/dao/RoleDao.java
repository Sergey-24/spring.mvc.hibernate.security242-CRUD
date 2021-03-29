package web.dao;

import web.entity.Role;
import web.entity.User;

import java.util.List;

public interface RoleDao {

    List<Role> getRoles(String role);

    List<Role> findAllRoles();

    void saveRole(Role role);

    Role findRoleByName(String roleName);
}
