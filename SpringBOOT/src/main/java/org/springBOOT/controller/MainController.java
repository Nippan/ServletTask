package org.springBOOT.controller;

import org.springBOOT.model.Message;
import org.springBOOT.model.User;
import org.springBOOT.repos.MessageRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MainController {

    @Autowired
    private MessageRepo messageRepo;

    @GetMapping("/")
    public String getLoginPage() {
        return "home";
    }

    @GetMapping("/message")
    public String main(@RequestParam(required = false) String filter, Model model) {
        Iterable<Message> all = messageRepo.findAll();

        if (filter != null && !filter.isEmpty()) {
            all = messageRepo.findByTag(filter);
        } else {
            all = messageRepo.findAll();
        }

        model.addAttribute("filter", filter);
        model.addAttribute("messages", all);
        return "message";
    }

    @PostMapping("/message")
    public String add(@AuthenticationPrincipal User user,
            @RequestParam String text,
            @RequestParam String tag, Model model) {
        Message message = new Message(text, tag, user);
        messageRepo.save(message);
        Iterable<Message> all = messageRepo.findAll();
        model.addAttribute("messages", all);
        return "message";
    }

}
