package com.in2horizon.escore.model;


import lombok.*;
import org.springframework.lang.NonNull;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.Set;

@Entity
@ToString
@RequiredArgsConstructor(access = AccessLevel.PUBLIC)
@NoArgsConstructor(access = AccessLevel.PUBLIC, force = true)


@Table(name = "users", uniqueConstraints = {@UniqueConstraint(columnNames = {"username", "password", "email"})})
public class User implements UserDetails {

    @Setter
    @Getter
    @Id

    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Setter
    @Getter
    @NonNull
    private String username;

    @Setter
    @Getter
    @NonNull
    private String password;

    @Setter
    @Getter
    @NonNull
    private String email;

    @NonNull
    @ManyToMany(fetch = FetchType.EAGER/*,cascade = CascadeType.ALL*/)
    @JoinTable(name = "user_authority", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "auth_id"))
    private Set<Authority> authorities;




    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
           return authorities;
    }


    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
