package com.in2horizon.escore.service;

import com.in2horizon.escore.model.CompetitionRepository;
import com.in2horizon.escore.model.User;
import com.in2horizon.escore.model.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
public class UserService {
    @Autowired
    UserRepository userRepo;
    @Autowired
    CompetitionRepository compRepo;

    final String TAG="UserService: ";

    public Iterable<User> getUsers() {
        Iterable<User> users = userRepo.findAll();
        log.info(TAG+"loaded users: " + users);
        return users;
    }

    public ResponseEntity<User> getUser(Long id) {
        Optional<User> user = userRepo.findById(id);
        if (user.isPresent()) {
            return ResponseEntity.ok(user.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    public ResponseEntity<String> addUser(User user) {
        User existing = userRepo.findById(user.getId()).orElse(null);
        if (existing == null) {
            if (user.getUsername().isEmpty()
                    || user.getPassword().isEmpty()
                    || user.getEmail().isEmpty()) {
                return new ResponseEntity<String>("Please fill all required fields", HttpStatus.BAD_REQUEST);
            }
            log.info(userRepo.save(user).toString());
            return ResponseEntity.ok("User added");
        }
        return new ResponseEntity<String>("User already exists",HttpStatus.CONFLICT);
    }


    public ResponseEntity<String> updateUser( User user) {
        User existingUser = userRepo.findById(user.getId()).orElse(null);
        if (existingUser != null) {

            existingUser.setUsername(user.getUsername());
            existingUser.setPassword(user.getPassword());
            existingUser.setEmail(user.getEmail());
            //   existingUser.setAuthorities(user.getAuthorities());

            userRepo.save(existingUser);
            return ResponseEntity.ok("user updated");
        }
        return new ResponseEntity<>("User not found", HttpStatus.NOT_FOUND);


    }

    public ResponseEntity deleteUser(Long id) {

        Optional<User> user = userRepo.findById(id);
        if (user.isPresent()) {
     //       if (roleRepo.findAllByUser(user.get()).isEmpty()) {

            if(compRepo.findWhereContainsUser(user.get()).isEmpty()){

                userRepo.deleteById(id);
                return new ResponseEntity(HttpStatus.OK);

            } else {
                return new ResponseEntity(HttpStatus.PARTIAL_CONTENT);
            }
        } else {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }

    }

}
