package com.in2horizon.escore.model;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
@RequiredArgsConstructor
@Table(name = "competitions")
public class Competition {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(unique = true)
    private final String name;

    @Column(columnDefinition = "boolean default false")
    private final Boolean archived = false;

    /*
        @ManyToOne(fetch = FetchType.EAGER,optional = false)
        @JoinColumn
        private final User admin;
    */

    /*
        @ManyToMany(mappedBy = "competitions", fetch = FetchType.LAZY)
        private Set<User> users = new HashSet<>();
    */

    /*
        @OneToMany(mappedBy = "competition",cascade = CascadeType.PERSIST)
        Set<CUA> competitionUserAuthorities;
    */

    /*
    public Competition(String name) {
        this.name = name;
    }*/
}
