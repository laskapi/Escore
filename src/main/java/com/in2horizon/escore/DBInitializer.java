package com.in2horizon.escore;

import com.in2horizon.escore.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Collections;
import java.util.List;
import java.util.Set;


@Configuration

public class DBInitializer {

    @Autowired
    UserRepository userRepository;
    @Autowired
    AuthorityRepository authRepository;
    @Autowired
    CompetitionRepository compRepository;
    @Autowired
    PasswordEncoder encoder;
    /*
        @Autowired
        private AuthUserDetailsRepository authUserDetailsRepository;
        @Autowired
        private AuthGrantedAuthorityRepository authGrantedAuthorityRepository;
*/

    /*       @Autowired
           private PasswordEncoder passwordEncoder;
   */
    // initialize the user in DB
    @Bean
    public CommandLineRunner initializeJpaData() {
        return (args) -> {
            System.out.println("application started");

            Authority authSuper = new Authority("SUPER");
            authSuper=authRepository.save(authSuper);
            Authority authAdmin = new Authority("ADMIN");
            authAdmin=authRepository.save(authAdmin);

            User user1 = new User("super", encoder.encode("super")
                    ,  List.of(authSuper));
            user1=userRepository.save(user1);

            User user2 = new User( "admin", encoder.encode("admin")
                    ,  List.of(authAdmin));
            user2=userRepository.save(user2);

            Competition competition = new Competition( "violin", user1);
            compRepository.save(competition);
            competition = new Competition( "cello", user2);
            compRepository.save(competition);


     /*           user.setUsername("user2");
                user.setPassword(passwordEncoder.encode("password"));
                user.setEnabled(true);
                user.setCredentialsNonExpired(true);
                user.setAccountNonExpired(true);
                user.setAccountNonLocked(true);

                AuthGrantedAuthority grantedAuthority = new AuthGrantedAuthority();
                grantedAuthority.setAuthority("USER");
                grantedAuthority.setAuthUserDetail(user);
                authUserDetailsRepository.save(user);
                authGrantedAuthorityRepository.save(grantedAuthority);
                user.setAuthorities(Collections.singleton(grantedAuthority));
     */
        };

    }
}

