package com.in2horizon.escore.model;

import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
//@EqualsAndHashCode(exclude = "users")

@NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
@RequiredArgsConstructor(access = AccessLevel.PUBLIC)

@Table(name = "authorities")
public class Authority implements GrantedAuthority {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private final String authority;

/*
    @ManyToMany(mappedBy = "authorities")
    private Set<User> users = new HashSet<>();
*/


    @Override
    public String getAuthority() {
        return authority;
    }
}
