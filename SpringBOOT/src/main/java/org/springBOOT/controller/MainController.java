package org.springBOOT.controller;

import org.springBOOT.domain.Message;
import org.springBOOT.domain.User;
import org.springBOOT.repos.MessageRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class MainController {

    @Autowired
    private MessageRepo messageRepo;

    @GetMapping("/")
    public String getLoginPage() {
        return "greeting";
    }

    @GetMapping("/index")
    public String main(Model model) {
        Iterable<Message> all = messageRepo.findAll();
        model.addAttribute("messages", all);
        return "index";
    }

    @PostMapping("/index")
    public String add(@AuthenticationPrincipal User user,
            @RequestParam String text,
            @RequestParam String tag, Model model) {
        Message message = new Message(text, tag, user);
        messageRepo.save(message);
        Iterable<Message> all = messageRepo.findAll();
        model.addAttribute("messages", all);
        return "index";
    }

    @PostMapping("filter")
    public String filter(@RequestParam String filter, Model model) {
        List<Message> messages = messageRepo.findByTag(filter);
        model.addAttribute("messages", messages);
        return "index";
    }
}
