package com.in2horizon.escore.controller;

import com.in2horizon.escore.model.*;
import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@RestController
public class SuperController {

    @Autowired
    CompetitionRepository compRepo;
    @Autowired
    UserRepository userRepo;
    @Autowired
    AuthorityRepository authRepo;
    @Autowired
    PasswordEncoder encoder;

    Logger logger = LoggerFactory.getLogger(SuperController.class);

    @GetMapping("/api/user")
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

    @GetMapping("/api/users")
    public Iterable<User> getUsers() {
        return userRepo.findAll();
    }

    @GetMapping("/api/competitions")
    public JSONArray getCompetitions() {
        JSONArray jArray = new JSONArray();
        List<Competition> result = StreamSupport.stream(compRepo.findAll().spliterator(), false).collect(Collectors.toList());
        jArray.addAll(result);
        return jArray;
    }

    //@CrossOrigin("*")
    @PostMapping("/api/competitions")
    public String addCompetition(@RequestBody JSONObject/*Map<String,String>*/ jsonComp) {

        Authority auth = authRepo.findByAuthority("ADMIN");
        User user;
        String rawPass;
        do {
            rawPass=generatePassword(100);

            user = new User(jsonComp.getAsString("admin"), encoder.encode(rawPass), auth);

        } while (!userRepo.findByUsernameAndPassword(user.getUsername(), user.getPassword()).isEmpty());


        try {
            user = userRepo.save(user);
            Competition competition = new Competition(jsonComp.getAsString("comp"), user);
            compRepo.save(competition);
            return rawPass;
        } catch (Exception e) {
            logger.error("Error creating competition: " + e.getMessage());

        }

      /*  System.out.println("after add:");
        compRepo.findAll().forEach(us -> System.out.println(us.toString()));
*/
        return null;//JSONObject.toJSONString(jsonComp);
    }

    @DeleteMapping("/api/super/{id}")
    public void deleteCompetition(@PathVariable Long id) {
        Competition comp = (compRepo.findById(id)).get();
        compRepo.delete(comp);

       /* System.out.println("after delete:");
        compRepo.findAll().forEach(us -> System.out.println(us.toString()));
*/
    }

    private String generatePassword(int range) {
        Random random = new Random();
        String password = Integer.toString(random.nextInt(range));
        return password;
    }
}

