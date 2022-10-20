package com.in2horizon.escore;

import com.in2horizon.escore.model.User;
import com.in2horizon.escore.model.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserRepositoryUserDetailsService implements UserDetailsService {
    private final UserRepository userRepo;

    @Autowired
public UserRepositoryUserDetailsService(UserRepository userRepo){
    this.userRepo=userRepo;
}

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user=userRepo.findByUsername(username).get(0);
        if(user!=null){
            return user;
        }
        throw new UsernameNotFoundException("Użytkownik "+ username+ " nie istnieje");
    }
}
