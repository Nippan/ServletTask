package org.applic_spring.controller;

import org.applic_spring.model.Role;
import org.applic_spring.model.User;
import org.applic_spring.repos.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {
    ModelAndView modelAndView = new ModelAndView();

    @Autowired
    private UserRepo repository;

    @GetMapping
    public ModelAndView getAllUsers() {
        List<User> users = repository.findAll();
        modelAndView.setViewName("admin");
        modelAndView.addObject("users", users);
        return modelAndView;
    }

    @PostMapping("/add")
    public ModelAndView addUser(User user, String role) {
        User userFromDb = repository.findByUsername(user.getUsername());

        if (userFromDb != null) {
            modelAndView.addObject("message", "User exists!");
            modelAndView.addObject("users", repository.findAll());
        } else {
            user.setActive(true);
            user.setRoles(Collections.singleton(Role.valueOf(role)));
            repository.save(user);
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
        user.setUsername(username);
        user.setEmail(email);
        user.setPassword(password);
        repository.save(user);

        modelAndView.setViewName("redirect:/admin");
        return modelAndView;
    }

    @GetMapping("/delete/{user}")
    public ModelAndView userDelete(@PathVariable User user, Model model) {
        repository.delete(user);
        modelAndView.setViewName("redirect:/admin");
        return modelAndView;
    }

}
