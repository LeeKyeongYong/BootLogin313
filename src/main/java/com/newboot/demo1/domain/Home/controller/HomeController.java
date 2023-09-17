package com.newboot.demo1.domain.Home.controller;

import com.newboot.demo1.base.rq.Rq;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequiredArgsConstructor
public class HomeController {
    private final Rq rq;

    @GetMapping("/")
    public String showMain(){
        return "user/home/main";
    }

    @GetMapping("/admin")
    @PreAuthorize("hasAnyAuthority('admin')")
    public String showAdmin(){
        return "user/home/admin";
    }
    @GetMapping("cookies")
    @ResponseBody
    public String showCookies(){
        return rq.getAllCookieValuesAsString();
    }
    @GetMapping("/sessions")
    @ResponseBody
    public String showSessions(){
        return rq.getAllSessionValuesAsString();
    }

}
