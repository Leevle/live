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

    @GetMapping("/getLivePushCode")
    public String getLivePushCode(){
        return null;
    }

    @PostMapping("/registerNewLive")
    public String registerNewLive(Live live){
        return service.regisetr(live);
    }

    @PostMapping("/updatePushCode")
    public String updatePushCode(Live live){
        return service.updatePushCode(live);
    }

    @GetMapping("/getAllLive")
    public String getAllLive(){
        return null;
    }
}
