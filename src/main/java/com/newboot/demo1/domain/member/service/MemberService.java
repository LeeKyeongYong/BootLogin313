package com.newboot.demo1.domain.member.service;


import com.newboot.demo1.domain.member.repository.MemberRepository;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.newboot.demo1.domain.member.entity.Member;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberService {
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public Member join(String username,String password,String nickname){
        Member member = Member
                .builder()
                .username(username)
                .password(passwordEncoder.encode(password))
                .nickname(nickname)
                .build();
        return memberRepository.save(member);
    }

    public Optional<Member> findByUsername(String username){
        return memberRepository.findByUsername(username);
    }

    public Optional<Member> findById(long id){
        return memberRepository.findById(id);
    }

    @Transactional
    public void modify(Member member,String password,String nickname){
        if (password != null && password.length() > 0) {
            member.setPassword(passwordEncoder.encode(password));
        }

        if (nickname != null && nickname.length() > 0) {
            member.setNickname(nickname);
        }
    }
}
