package org.springCRUD.controller;

import java.util.List;
import org.springCRUD.model.User;
import org.springCRUD.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class UserController {
    private UserService userService;
    BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/admin")
    public ModelAndView getAllUsers() {
        List<User> users = this.userService.allUsers();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("home");
        modelAndView.addObject("usersFromServer", users);
        return modelAndView;
    }

    @RequestMapping(
            value = {"/admin/delete/{*}"},
            method = {RequestMethod.POST}
    )
    public String deleteUser(@PathVariable("*") Long userId) {
        this.userService.delete(userId);
        return "redirect:/admin";
    }

    @RequestMapping(
            path = {"/admin/edit/{*}"},
            method = {RequestMethod.POST}
    )
    public String editUser(User user, @PathVariable("*") Long userId) {
        User oldUser = this.userService.getById(userId);
        oldUser.setName(user.getName());
        oldUser.setPassword(this.encoder.encode(user.getPassword()));
        oldUser.setLogin(user.getLogin());
        this.userService.edit(oldUser);
        return "redirect:/admin";
    }

    @RequestMapping(
            path = {"/admin/save"},
            method = {RequestMethod.POST}
    )
    public String saveUsers(User user, String role) {
        user.setPassword(this.encoder.encode(user.getPassword()));
        this.userService.add(user, role);
        return "redirect:/admin";
    }

    @RequestMapping(
            path = {"/admin/save"},
            method = {RequestMethod.GET}
    )
    public ModelAndView save() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("save");
        return modelAndView;
    }
}