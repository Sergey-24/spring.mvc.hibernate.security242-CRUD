package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import web.entity.Role;
import web.entity.User;
import web.service.RoleService;
import web.service.UserService;
import java.util.List;
import java.util.Set;

@Controller
@Transactional
@RequestMapping("/admin")
public class AdminController {

    private final UserService userService;
    private final RoleService roleService;

    @Autowired
    public AdminController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping()
    public String showUsersForAdmin(Model model) {
        List<User> allUsers = userService.getAllUsers();
        model.addAttribute("users", allUsers);
        return "admin/user-list";
    }

    @GetMapping("/addNewUser")
    public String addNewUser(Model model) {
        Set<Role> roleSet = roleService.findAllRoles();
        User user = new User();
        model.addAttribute("user", user);
        model.addAttribute("roles", roleSet);
        return "admin/user-creat";
    }

    @PostMapping(value = "/addNewUser")
    public String saveUser(User user) {
        userService.saveUserByUser(user);
        return "redirect:/admin";
    }


    @GetMapping("/user-delete/{id}")
    public String deleteUser(@PathVariable("id") Long id) {
        userService.deleteUserById(id);
        return "redirect:/admin";
    }

    @GetMapping("/user-update/{id}")
    public String updateUserForm(@PathVariable("id") Long id, Model model) {
        User user = userService.findUserById(id);
        model.addAttribute("user", user);
        model.addAttribute("roles", roleService.findAllRoles());
        return "admin/user-update";
    }

    @PostMapping("/user-update")
    public String updateUser(User user) {
        userService.updateUserByUser(user);
        return "redirect:/admin";
    }
}
