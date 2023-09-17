package com.newboot.demo1.domain.member.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.ArrayList;
import java.util.List;

@Entity
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
public class Member {
    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String username;
    private String password;
    private String nickname;

    public boolean isAdmin(){
        return "admin".equals(username);
    }

    public List<? extends GrantedAuthority> getGrantedAuthorities(){
        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();

        grantedAuthorities.add(new SimpleGrantedAuthority("member"));

        if(isAdmin()){
            grantedAuthorities.add(new SimpleGrantedAuthority("admin"));
        }
        return grantedAuthorities;
    }
}
