package com.in2horizon.escore;

import com.in2horizon.escore.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

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

            Authority auth = new Authority(1L, "SUPER");
            authRepository.save(auth);
            auth = new Authority(2L, "ADMIN");
            authRepository.save(auth);

            User user = new User(1L, "super", encoder.encode("super")
                    , authRepository.findById(1L).get());
            userRepository.save(user);


            user = new User(2L, "admin", encoder.encode("admin")
                    , authRepository.findById(2L).get());
            userRepository.save(user);


            Competition competition = new Competition(0L, "violin", userRepository.findById(1L).get());
            compRepository.save(competition);
            competition = new Competition(1L, "cello", userRepository.findById(1L).get());
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

