package com.in2horizon.escore.controller;

import com.in2horizon.escore.model.*;
import lombok.extern.slf4j.Slf4j;
import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Field;
import java.util.*;

@Slf4j
@RestController
@CrossOrigin(value = "http://localhost:4200")
public class SuperController {

    @Autowired
    CompetitionRepository compRepo;
    @Autowired
    UserRepository userRepo;
    @Autowired
    AuthorityRepository authRepo;
    @Autowired
    PasswordEncoder encoder;

    //Logger logger = LoggerFactory.getLogger(SuperController.class);

    @GetMapping("/users")
    public Iterable<User> getUsers() {
        return userRepo.findAll();
    }


    @GetMapping("/competitions")
    public Iterable<Competition> getCompetitions() {
        return compRepo.findAll();

        }

    @GetMapping("/competitions/{user}")
    public Iterable<Competition> getCompetitionsForAdmin(@PathVariable String user) {
             return compRepo.findByAdminUsername(user);
    }

        @PostMapping("/competitions")
    public void addCompetition(@RequestBody JSONObject/*Map<String,String>*/ jsonComp) {

        try {
            User admin = userRepo.findByUsername(jsonComp.getAsString("admin")).get(0);
            Competition competition = new Competition(jsonComp.getAsString("name"), admin);

            compRepo.save(competition);

        } catch (IndexOutOfBoundsException e) {
            e.printStackTrace();
        }

    }

    @DeleteMapping("/super/{id}")
    public void deleteCompetition(@PathVariable Long id) {
        Competition comp = (compRepo.findById(id)).get();
        compRepo.delete(comp);

    }

    @PatchMapping("/competitions/{competitionId}")
    ResponseEntity<Competition> patchCompetition(@PathVariable Long competitionId, @RequestBody /*Map<String, Object>*/ Competition comp) {
        log.info("tererereerer"+ comp);

        compRepo.save(comp);
       return  new ResponseEntity<Competition>(comp, HttpStatus.OK);
    }

/*

    @PatchMapping("/competitions/{competitionId}")
    ResponseEntity<Competition> patchCompetition(@PathVariable Long competitionId, @RequestBody Map<String, Object> fields) {
        log.info("tererereerer"+ fields);


        if (competitionId <= 0 || fields == null || fields.isEmpty() || !fields.get("id").equals(competitionId)){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        Competition comp = (compRepo.findById(competitionId)).get();

        if( comp == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }


        fields.forEach((k, v) -> {
            // use reflection to get field k on object and set it to value v

            Field field = ReflectionUtils.findField(Competition.class, k); // find field in the object class
            field.setAccessible(true);
            ReflectionUtils.setField(field, comp, v); // set given field for defined object to value V
        });

        compRepo.save(comp);
        return new ResponseEntity<>(comp, HttpStatus.OK);
    }
*/




    private String generatePassword(int range) {
        Random random = new Random();
        String password = Integer.toString(random.nextInt(range));
        return password;
    }


}






















