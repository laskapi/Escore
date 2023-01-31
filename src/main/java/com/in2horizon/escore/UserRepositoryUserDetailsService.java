package com.in2horizon.escore;

//import com.in2horizon.escore.service.DataService;

import com.in2horizon.escore.model.User;
import com.in2horizon.escore.model.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserRepositoryUserDetailsService implements UserDetailsService {

    //@Autowired
    //DataService dataService;

    @Autowired
    UserRepository userRepository;

    /*
        @Autowired
    public UserRepositoryUserDetailsService(DataService dataService){
        this.dataService=dataService;
    }
    */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = userRepository.findByUsername(username).get(0);//dataService.getUserByName(username);
        if (user != null) {
            return user;
        }
        throw new UsernameNotFoundException("Użytkownik " + username + " nie istnieje");
    }
}
