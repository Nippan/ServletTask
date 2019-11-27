package org.applic_spring.controller;

import org.applic_spring.model.Role;
import org.applic_spring.model.User;
import org.applic_spring.repos.UserRepo;
import org.applic_spring.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {
    ModelAndView modelAndView = new ModelAndView();

    @Autowired
    private UserService userService;

    @GetMapping
    public ModelAndView getAllUsers() {
        List<User> users = userService.getUsers();
        modelAndView.setViewName("admin");
        modelAndView.addObject("users", users);
        return modelAndView;
    }

    @PostMapping("/add")
    public ModelAndView addUser(User user, String role) {
        User userFromDb = userService.getByName(user.getUsername());

        if (userFromDb != null) {
            modelAndView.addObject("message", "User exists!");
            modelAndView.addObject("users", userService.getUsers());
        } else {
            userService.addUser(user, role);
        }

        modelAndView.setViewName("redirect:/admin");
        return modelAndView;
    }

    @PostMapping("/edit")
    public ModelAndView editSave(
            @RequestParam String username,
            @RequestParam String password,
            @RequestParam String email,
            @RequestParam("userId") User user
    ) {
        userService.editUser(user, username, email, password);
        modelAndView.setViewName("redirect:/admin");
        return modelAndView;
    }

    @GetMapping("/delete/{user}")
    public ModelAndView userDelete(@PathVariable User user) {
        userService.deleteUser(user);
        modelAndView.setViewName("redirect:/admin");
        return modelAndView;
    }

}
