package com.in2horizon.escore.controller;

import com.in2horizon.escore.model.*;
import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@RestController
public class SuperController {

    @Autowired
    CompetitionRepository compRepo;
    @Autowired
    UserRepository userRepo;

    @GetMapping("/user")
    public JSONObject getAuthority(@AuthenticationPrincipal User user) {
/*
        JSONArray jsonArray = new JSONArray();
        user.getAuthorities().stream().forEach(auth->jsonArray.appendElement(auth.getAuthority()));
        return jsonArray;
*/
//        return user.getAuthorities().iterator().next().getAuthority();

        JSONObject jObject = new JSONObject();
        jObject.appendField("authority", user.getAuthorities().iterator().next().getAuthority());
        return jObject;
    }

    @GetMapping("/api/super/")
    public Iterable<User> getUsers(){
        return userRepo.findAll();
    }

    @GetMapping("/super")
    public JSONArray getSuper() {
        JSONArray jArray = new JSONArray();
        List<Competition> result = StreamSupport.stream(compRepo.findAll().spliterator(), false).collect(Collectors.toList());
        jArray.addAll(result);
        return jArray;
    }

    @DeleteMapping("/super/{id}")
    public void deleteCompetition(@PathVariable Long id) {
       System.out.println("id=  "+id);
        Competition comp = (compRepo.findById(id)).get();
        compRepo.delete(comp);

    }
    /*    JSONObject sampleJSON= new JSONObject();
        sampleJSON.put("name", "Hello");
        sampleJSON.put("age", 23);
        return sampleJSON;
    */
}

