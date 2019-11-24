package org.applic_spring.controller;

import org.applic_spring.model.Role;
import org.applic_spring.model.User;
import org.applic_spring.repos.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private UserRepo repository;

    @GetMapping
    public Iterable<User> findAll() {
        return repository.findAll();
    }

    @PostMapping("/add")
    public String addUser(User user, String role, Model model) {
        User userFromDb = repository.findByUsername(user.getUsername());

        if (userFromDb != null) {
            model.addAttribute("message", "User exists!");
            model.addAttribute("users", repository.findAll());
            return "admin";
        } else {
            user.setActive(true);
            user.setRoles(Collections.singleton(Role.valueOf(role)));
            repository.save(user);
        }

        return "redirect:/admin";
    }

    @PostMapping("/edit")
    public String editSave(
            @RequestParam String username,
            @RequestParam String password,
            @RequestParam String email,
            @RequestParam("userId") User user
    ) {
        user.setUsername(username);
        user.setEmail(email);
        user.setPassword(password);
        repository.save(user);

        return "redirect:/admin";
    }

    @GetMapping("/delete/{user}")
    public String userDelete(@PathVariable User user, Model model) {
        repository.delete(user);
        return "redirect:/admin";
    }

}
