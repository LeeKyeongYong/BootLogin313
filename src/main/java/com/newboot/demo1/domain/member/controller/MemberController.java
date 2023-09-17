package com.newboot.demo1.domain.member.controller;

import com.newboot.demo1.base.rq.Rq;
import com.newboot.demo1.domain.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/usr/member")
@RequiredArgsConstructor
public class MemberController {
    private final Rq rq;
    private MemberService memberService;

    @GetMapping("/login")
    @PreAuthorize("isAnonymous()")
    public String showLogin(){
        return "user/member/login";
    }

    @GetMapping("/me")
    @PreAuthorize("isAuthenticated()")
    public String showMe(){
        return "user/member/me";
    }
}
