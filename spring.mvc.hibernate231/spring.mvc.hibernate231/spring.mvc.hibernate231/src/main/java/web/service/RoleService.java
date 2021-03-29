package web.service;


import web.entity.Role;

import java.util.List;
import java.util.Set;

public interface RoleService {
    List<Role> getRoles(String role);

    List<Role> findAllRoles();

    void saveRole(Role role);

    public Role findRoleById(String rolename);
}
