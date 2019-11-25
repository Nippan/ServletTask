package org.applic_spring.controller;

import javassist.tools.web.BadHttpRequest;
import org.applic_spring.model.Role;
import org.applic_spring.model.User;
import org.applic_spring.repos.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private UserRepo repository;

    @GetMapping
    public List<User> getUsers() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getEmployeeById(@PathVariable(value = "id") Long employeeId)
            throws Exception {
        User employee = repository.findById(employeeId).get();
        return ResponseEntity.ok().body(employee);
    }

    @PostMapping
    public User create(@Valid @RequestBody User user) {
        user.setActive(true);
        user.setRoles(Collections.singleton(Role.valueOf("USER")));
        return repository.save(user);
    }

    @PutMapping(path = "/{id}")
    public User update(@PathVariable(value = "id") User user,
                       @Valid @RequestBody User userDetails) throws BadHttpRequest {
        user.setUsername(userDetails.getUsername());
        user.setEmail(userDetails.getEmail());
        user.setPassword(userDetails.getPassword());
        return repository.save(user);
    }

    @DeleteMapping(path = "/{id}")
    public void delete(@PathVariable(value = "id") Long userId) {
        User user = repository.findById(userId).get();
        repository.delete(user);
    }

}
