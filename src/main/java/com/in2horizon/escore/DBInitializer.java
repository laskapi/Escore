package com.in2horizon.escore;

import com.in2horizon.escore.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

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

    @Bean
    public CommandLineRunner initDB() {
        return (args) -> {
            System.out.println("application started - commandLineRunner ");

            //authorities initialisation
            Authority authSuper = new Authority("SUPER");
            authSuper=authRepository.save(authSuper);
            Authority authAdmin = new Authority("ADMIN");
            authAdmin=authRepository.save(authAdmin);
            Authority authUser = new Authority("USER");
            authUser=authRepository.save(authUser);
            //--------------------------
//--------------------------------------

            User user1 = new User("super", encoder.encode("super"),
                          "super@in2horizon.com"  ,  Set.of(authSuper));
            user1=userRepository.save(user1);

            User user2 = new User( "admin", encoder.encode("admin"),
                    "admin@in2horizon.com",  Set.of(authAdmin));
            user2=userRepository.save(user2);


            User user3 = new User( "delete", encoder.encode("delete"),
                    "delete@in2horizon.com",  Set.of(authAdmin));
            userRepository.save(user3);



            Competition competition1 = new Competition( "violin",user1,Set.of(user2,user3));
            compRepository.save(competition1);
            Competition competition2 = new Competition( "cello",user2,Set.of(user1,user2));
            compRepository.save(competition2);





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

