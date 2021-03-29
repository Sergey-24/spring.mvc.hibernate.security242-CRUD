package web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import web.dao.RoleDao;
import web.entity.Role;
import java.util.List;



@Service
public class RoleServiceImpl implements RoleService {

   private RoleDao roleDao;

   @Autowired
   public void setRoleDao(RoleDao roleDao) {
      this.roleDao = roleDao;
   }

   @Override
   @Transactional
   public List<Role> getRoles(String role) {
      return roleDao.getRoles(role);
   }

   @Override
   @Transactional
   public List<Role> findAllRoles() {
      return roleDao.findAllRoles();
   }

   @Override
   @Transactional
   public void saveRole(Role role) {
      roleDao.saveRole(role);
   }

   @Override
   @Transactional
   public Role findRoleById(String rolename) {
      return roleDao.findRoleByName(rolename);
   }
}
