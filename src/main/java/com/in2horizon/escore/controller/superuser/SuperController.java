package com.in2horizon.escore.controller.superuser;

import com.in2horizon.escore.model.Competition;
import com.in2horizon.escore.model.User;
import com.in2horizon.escore.service.CompetitionService;
import com.in2horizon.escore.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/super")
@RestController
public class SuperController {
    @Autowired
    UserService userService;
    @Autowired
    CompetitionService compService;


    @GetMapping("/users")
    public List<User> getUsers() {
        return userService.getUsers();
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<User> getUser(@PathVariable("id") Long id) {
        return userService.getUser(id);
    }

    @PostMapping(value = "/users", produces = "application/json")
    public ResponseEntity<User> addUser(@RequestBody User user) {
        return userService.addUser(user);
    }

    @PutMapping("/users")
    public ResponseEntity<User> updateUser(@RequestBody User user) {
        return userService.updateUser(user);
    }

    @DeleteMapping(value = "/users/{id}")
    public ResponseEntity deleteUser(@PathVariable Long id) {
        return userService.deleteUser(id);
    }

    @GetMapping("/competitions")
    public List<Competition> getCompetitions() {
        return compService.getCompetitions();
    }

    @GetMapping("/competitions/{id}")
    public ResponseEntity<Competition> getCompetition(@PathVariable Long id) {
        return compService.getCompetition(id);
    }

    @PostMapping(value = "/competitions")
    public ResponseEntity<Competition> addCompetition(@RequestBody Competition comp) {
        return compService.addCompetition(comp);
    }

    @PutMapping("/competitions")
    public ResponseEntity<Competition> updateCompetition(@RequestBody Competition comp) {
        return compService.updateCompetition(comp);
    }

    @DeleteMapping("/competitions/{id}")
    public ResponseEntity<Void> deleteCompetition(@PathVariable Long id) {
        return compService.deleteCompetition(id);
    }
}