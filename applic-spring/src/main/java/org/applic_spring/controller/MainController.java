package org.applic_spring.controller;

import org.applic_spring.model.User;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class MainController {

    @GetMapping("/")
    public String getLoginPage(Authentication authentication) {
        if (authentication != null) {
            return "redirect: /user";
        }
        return "login";
    }

    @GetMapping("/login")
    public String getLoginPage (Authentication authentication, ModelMap model, HttpServletRequest request) {
        if (authentication != null) {
            return "redirect: /user";
        }
        if (request.getParameterMap().containsKey("error")) {
            model.addAttribute("error", true);
        }
        return "login";
    }

    @PostMapping("/login")
    public void getPrincepal(Authentication authentication, Model model) {
        User user = (User) authentication.getPrincipal();
        model.addAttribute("princ", user);
    }

    @GetMapping("/user")
    public String getUserPage(Authentication authentication, Model model) {
        model.addAttribute("user", authentication.getName());
        return "user";
    }
}
