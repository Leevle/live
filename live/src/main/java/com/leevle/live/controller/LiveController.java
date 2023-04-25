package com.leevle.live.controller;

import com.leevle.live.model.Live;
import com.leevle.live.service.ControlService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController

public class LiveController {

    @Resource
    ControlService controlService;


    @GetMapping("/updatePushToken/{uuid}")
    public String updatePushToken(@PathVariable String uuid){
        return controlService.updatePushCode(uuid);
    }

    @GetMapping("/getPullCode/{uuid}")
    public String getPullCode(@PathVariable String uuid){
        return controlService.getPushCode(uuid,false);
    }


    @GetMapping("/getPushUrl/{uuid}")
    public String getPushUrl(@PathVariable String uuid){return controlService.getPushCode(uuid,true);}

    @PostMapping("/register/{uuid}")
    public String register(@PathVariable String uuid){
        return controlService.register(uuid);
    }

}
