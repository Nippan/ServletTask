package org.applic_spring.controller;

import org.applic_spring.model.User;
import org.applic_spring.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminRestController {

    @Autowired
    private UserService userService;

    private static final Logger logger = LoggerFactory.getLogger(User.class);

    @GetMapping("/users")
    public List<User> getAll() {
        List<User> users = userService.getUsers();
        return users;
    }

    @GetMapping("/{name}")
    public User getUser(@PathVariable(value = "name") String userName) {
        return userService.getUserByName(userName);
    }

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user) {
        userService.addUser(user, "USER");
        logger.info(user.toString() + " - Created");
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> editUser(@PathVariable Long id, @RequestBody User userDetails) {
        User user = userService.editUser(id, userDetails);
        logger.info(user.toString() + " - Updated");
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") User user) {
        userService.deleteUser(user);
        logger.info(user.toString() + " - Deleted");
    }
}
