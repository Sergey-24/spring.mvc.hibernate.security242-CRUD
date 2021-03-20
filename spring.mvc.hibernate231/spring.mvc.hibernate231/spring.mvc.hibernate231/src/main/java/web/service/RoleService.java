package web.service;


import web.entity.Role;
import java.util.Set;

public interface RoleService {
    Role getRoles(String role);

    Set<Role> findAllRoles();

    void saveRole(Role role);
}
