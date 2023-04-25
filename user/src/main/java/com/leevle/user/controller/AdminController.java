package com.leevle.user.controller;

import com.leevle.user.feign.liveServer;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class AdminController {
    @Resource
    liveServer liveServer;
    @PostMapping("/ban")
    public String ban(String uuid,Boolean ban){
        return liveServer.ban(uuid,ban);
    }
}
