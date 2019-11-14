package org.springBOOT.controller;

import org.springBOOT.model.User;
import org.springBOOT.repos.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/user")
@PreAuthorize("hasAuthority('ADMIN')")
public class UserController {
    @Autowired
    private UserRepo userRepo;

    @GetMapping
    public String userList(Model model, HttpServletRequest request) {
        model.addAttribute("users", userRepo.findAll());
        return "userList";
    }

    @GetMapping("{user}")
    public String userEditForm(@PathVariable User user, Model model) {
        model.addAttribute("user", user);
        return "userEdit";
    }

    @GetMapping("/delete/{user}")
    public String userDelete(@PathVariable User user, Model model) {
        userRepo.delete(user);
//        model.addAttribute("users", userRepo.findAll());
        return "redirect:/user";
    }

    @PostMapping
    public String userSave(
            @RequestParam String username,
            @RequestParam String password,
            @RequestParam("userId") User user
    ) {
        user.setUsername(username);
        user.setPassword(password);
        userRepo.save(user);

        return "redirect:/user";
    }
}
