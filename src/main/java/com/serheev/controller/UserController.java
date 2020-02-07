package com.serheev.controller;

import com.serheev.dto.User;
import com.serheev.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping(value = "id/{id}")
    @PreAuthorize("hasRole('ROLE_LESSON_ADMIN') or hasRole('ROLE_LESSON_USER')")
    public ResponseEntity<?> getUserById(@PathVariable("id") Long id) {
        return new ResponseEntity<>(userService.findUserById(id), HttpStatus.OK);
    }

    @GetMapping(value = "login/{login}")
    @Secured("ROLE_LESSON_ADMIN")
    public ResponseEntity<?> getUserByLogin(@PathVariable("login") String login) {
        return new ResponseEntity<>(userService.findUserByLogin(login), HttpStatus.OK);
    }

    @PostMapping(value = "create")
    @ResponseStatus(HttpStatus.CREATED)
    @Secured("ROLE_LESSON_ADMIN")
    public User createUser(@RequestBody User user) {
        return userService.create(user);
    }

    @PutMapping(value = "update/{id}")
    @Secured("ROLE_LESSON_ADMIN")
    public ResponseEntity<?> updateUser(@PathVariable("id") Long id, @RequestBody User user) {
        userService.update(id, user);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping(value = "remove/{id}")
    @Secured("ROLE_LESSON_ADMIN")
    public ResponseEntity<?> removeUser(@PathVariable("id") Long id) {
        userService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
