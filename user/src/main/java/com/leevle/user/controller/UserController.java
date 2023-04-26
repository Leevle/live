package com.leevle.user.controller;

import com.leevle.user.feign.LiveServer;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@RestController
public class UserController {

    @Resource
    LiveServer liveServer;
    @GetMapping("/updatePushToken")
    public String updatePushToken(HttpServletRequest request){
        return liveServer.updatePushToken(request.getAttribute("uuid").toString());
    }

    @GetMapping("/getPushUrl")
    public String getPushUrl(HttpServletRequest request){
        return liveServer.getPushUrl(request.getAttribute("uuid").toString());
    }

    @GetMapping("/stopLive")
    public String stopLive(HttpServletRequest request){
        return liveServer.stopLive(request.getAttribute("uuid").toString());
    }

    @PostMapping("/ban")
    public String AdminBan(HttpServletRequest request, @RequestParam String uuid, @RequestParam Boolean ban){
        return liveServer.ban(uuid,ban);
    }
}
