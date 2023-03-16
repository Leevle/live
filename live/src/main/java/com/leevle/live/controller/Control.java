package com.leevle.live.controller;

import com.leevle.live.model.Live;
import com.leevle.live.service.ControlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController

public class Control {

    @Autowired
    ControlService service;

    @PostMapping("/registerNewLive")
    public String registerNewLive(Live live){
        return service.regisetr(live);
    }

    //权限验证:普通用户
    @PostMapping("/updatePushCode")
    public String updatePushCode(Live live){
        return service.updatePushCode(live);
    }

    @PostMapping("/getPullCode")
    public String getPullCode(Live live){
        return service.getPushCode(live,false);
    }

    //权限验证:普通用户
    @PostMapping("/getPushUrl")
    public String getPushUrl(Live live){return service.getPushCode(live,true);}
//    @GetMapping("")
}
