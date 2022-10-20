package com.in2horizon.escore.model;

import lombok.AccessLevel;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;


import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor(access = AccessLevel.PRIVATE,force = true)
@RequiredArgsConstructor
public class Competition {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(unique = true)
    private final String name;
    @ManyToOne(fetch = FetchType.EAGER,optional = false)
    @JoinColumn
    private final User admin;
    @Column(columnDefinition = "boolean default false")
    private final Boolean archived=false;
}
