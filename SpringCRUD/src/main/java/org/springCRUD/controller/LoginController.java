package org.springCRUD.controller;

import javax.servlet.http.HttpServletRequest;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class LoginController {

    @GetMapping("/login")
    public String getLoginPage (Authentication authentication, ModelMap model, HttpServletRequest request) {
        if (authentication != null) {
            //model.addAttribute("user", request.getParameter("name"));
            return "redirect: /user";
        }
        if (request.getParameterMap().containsKey("error")) {
            model.addAttribute("error", true);
        }
        return "login";
    }

    @GetMapping("/user")
    public ModelAndView getIndexPage (Authentication authentication, ModelMap model, HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("index");
        modelAndView.addObject("user", authentication.getName());
        return modelAndView;
    }

    @GetMapping("/")
    public String getLoginPage(Authentication authentication) {
        if (authentication != null) {
            //model.addAttribute("user", request.getParameter("name"));
            return "redirect: /user";
        }
        return "login";
    }
}
