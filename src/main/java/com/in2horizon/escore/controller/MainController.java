package com.in2horizon.escore.controller;

import com.in2horizon.escore.model.AuthorityRepository;
import com.in2horizon.escore.model.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@CrossOrigin(value = "http://localhost:4200")
public class MainController {

    @Autowired
    AuthorityRepository authRepo;

    @GetMapping("/login")
public User login(@AuthenticationPrincipal User user){
        return user;
    }

    @GetMapping("/authorities")
    public Iterable getAuthorities(){
        return authRepo.findAll();

    }
}
