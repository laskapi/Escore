package com.in2horizon.escore.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
@RequiredArgsConstructor(access = AccessLevel.PUBLIC)
//@AllArgsConstructor(access = AccessLevel.PRIVATE)

public class Authority implements GrantedAuthority {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private final String authority;

 /*  public Authority(String authority){
        this.authority=authority;
    }*/
}
