package com.in2horizon.escore.model;

import lombok.*;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;

@Entity
@Data
//@EqualsAndHashCode(exclude = "users")

@NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
@RequiredArgsConstructor(access = AccessLevel.PUBLIC)
//@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Table(name = "authorities")
public class Authority implements GrantedAuthority {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private final String authority;

    /*@ManyToMany(mappedBy = "authorities")
    private Set<User> users = new HashSet<>();
    */

/*
    @OneToMany(mappedBy = "authority",cascade = CascadeType.PERSIST)
    Set<CUA> competitionUserAuthorities;
*/


    @Override
    public String getAuthority() {
        return authority;
    }
}
