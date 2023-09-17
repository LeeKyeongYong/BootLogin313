package com.newboot.demo1.base.security;

import com.newboot.demo1.domain.member.repository.MemberRepository;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import com.newboot.demo1.domain.member.entity.Member;

@Service
@RequiredArgsConstructor
@Transactional(readOnly  = true)
public class CustomUserDetailsService implements UserDetailsService {

    private final MemberRepository memberRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Member member = memberRepository.findByUsername(username).orElseThrow(()-> new UsernameNotFoundException("username(%s) not found".formatted(username)));
        return new User(member.getUsername(),member.getPassword(),member.getGrantedAuthorities());
    }
}
