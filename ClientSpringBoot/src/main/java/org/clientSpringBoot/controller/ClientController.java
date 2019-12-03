package org.clientSpringBoot.controller;

import org.clientSpringBoot.model.User;
import org.clientSpringBoot.service.RestClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/admin")
public class ClientController {

    @Autowired
    private RestClient restClient;

    @GetMapping
    public ModelAndView getAdmin() {
        ModelAndView model = new ModelAndView();
        model.setViewName("admin");
        return model;
    }

    @GetMapping("/users")
    public List<User> getAll() {
        return restClient.getAllUsers();
    }

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user) {
        restClient.addUser(user);
        System.out.println("User create");
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> editUser(@PathVariable Long id, @RequestBody User userDetails) {
        User user = restClient.update(id, userDetails);
        System.out.println("User updated");
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long id) {
        restClient.delete(id);
        System.out.println("User deleted");
    }

}
