package com.in2horizon.escore.controller;

import com.in2horizon.escore.model.User;
import lombok.extern.slf4j.Slf4j;
import net.minidev.json.JSONObject;
import org.springframework.security.core.annotation.AuthenticationPrincipal;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@Slf4j
@RestController
@CrossOrigin(value = "http://localhost:4200")
public class MainController {
    @GetMapping("/login")
    public JSONObject login(@AuthenticationPrincipal User user) {

        JSONObject auth = new JSONObject();
        auth.appendField("authority", user.getAuthorities().iterator().next().getAuthority());
        auth.appendField("username",user.getUsername());
        return auth;
    }

}
