package org.clientSpringBoot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MainController {

    @GetMapping("/")
    public String getLoginPage() {
        return "user";
    }

    @GetMapping("/user")
    public String getUserPage() {
        return "user";
    }
}
