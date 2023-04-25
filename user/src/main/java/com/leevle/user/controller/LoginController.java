package com.leevle.user.controller;

import com.leevle.user.service.LoginService;
import com.leevle.user.model.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@RestController
public class LoginController {
    @Resource
    LoginService loginService;
    @PostMapping("/register")
    public String register(@RequestBody User user){
        return loginService.register(user);
    }

    @PostMapping("/login")
    public String login(@RequestBody  User user){
        return loginService.login(user);
    }



}
