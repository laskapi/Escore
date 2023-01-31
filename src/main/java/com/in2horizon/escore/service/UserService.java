package com.in2horizon.escore.service;

import com.in2horizon.escore.model.CompetitionRepository;
import com.in2horizon.escore.model.User;
import com.in2horizon.escore.model.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class UserService {
    @Autowired
    UserRepository userRepo;
    @Autowired
    CompetitionRepository compRepo;

    final String TAG="UserService: ";




    public List<User> getUsers() {
        List<User> users = userRepo.findAll();
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

    public ResponseEntity<User> addUser(User user) {
        if(user.getId()==null){
            user.setId(-1L);
        }

        User existing = userRepo.findById(user.getId()).orElse(null);
        if(existing==null){
            existing=userRepo.findByEmail(user.getEmail()).orElse(null);
        }

        if (existing == null) {
            if (user.getUsername().isEmpty()
                    || user.getPassword().isEmpty()
                    || user.getEmail().isEmpty()) {
                    return new ResponseEntity(HttpStatus.BAD_REQUEST);
            }
            User saved=userRepo.save(user);
            log.info(saved.toString());
            return ResponseEntity.ok(saved);

        }
        return new ResponseEntity(HttpStatus.CONFLICT);

    }


    public ResponseEntity<User> updateUser( User user) {
        User existingUser = userRepo.findById(user.getId()).orElse(null);
        if (existingUser != null) {

            existingUser.setUsername(user.getUsername());
            existingUser.setPassword(user.getPassword());
            existingUser.setEmail(user.getEmail());

            User saved=userRepo.save(existingUser);
            return ResponseEntity.ok(saved);
        }
        return new ResponseEntity( HttpStatus.NOT_FOUND);


    }

    public ResponseEntity deleteUser(Long id) {

        Optional<User> user = userRepo.findById(id);
        if (user.isPresent()) {

            if(compRepo.findByUser(user.get()).isEmpty()){

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
