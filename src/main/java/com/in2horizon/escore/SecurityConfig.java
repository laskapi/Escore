package com.in2horizon.escore;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    /*
    @Autowired
    DataSource dataSource;
*/

    @Autowired
    private UserDetailsService userDetailsService;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

        auth.userDetailsService(userDetailsService);

       /* auth.jdbcAuthentication().dataSource(dataSource).usersByUsernameQuery("select username,password,enabled "
                + "from users "
                + "where username = ?")
                .authoritiesByUsernameQuery("select username,authority "
                        + "from authorities "
                        + "where username = ?");
       */


  /*      .withUser("user")
                .password(passwordEncoder().encode("user"))
                .roles("USER").and().withUser("admin").password(encoder().encode("admin"))
                .roles("USER","ADMIN");
  */
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.authorizeRequests()
                .antMatchers("/h2-console/**")
                .permitAll()
                .antMatchers("/super/**")
                .hasAnyAuthority("SUPER")
                .antMatchers("/**")
                .hasAnyAuthority("SUPER", "ADMIN")
                .and().httpBasic();


        http.cors();
        http.csrf().disable();
        http.headers().frameOptions().disable();
        //  http.csrf().csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse());
    }


    @Bean
    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }

}
