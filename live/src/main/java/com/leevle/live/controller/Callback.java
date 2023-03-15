package com.leevle.live.controller;

import com.alibaba.fastjson.JSONObject;
import com.leevle.live.service.CallbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class Callback {
    @Autowired
    CallbackService service;
    @PostMapping("streams")
    public String streams(@RequestBody String callbackInfo){
        JSONObject object=JSONObject.parseObject(callbackInfo);

        return service.stream(object);
    }
    @PostMapping("sessions")
    public String session(@RequestBody String callbackInfo){
        JSONObject object=JSONObject.parseObject(callbackInfo);

        return service.session(object);
    }
}
