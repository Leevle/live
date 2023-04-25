package com.leevle.live.controller;

import com.alibaba.fastjson.JSONObject;
import com.leevle.live.service.CallbackService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/v1")
public class CallbackController {
    @Resource
    CallbackService service;
    @PostMapping("streams")
    public String streams(@RequestBody String callbackInfo, HttpServletRequest request){
        JSONObject object=JSONObject.parseObject(callbackInfo);

        return service.stream(object);
    }
    @PostMapping("sessions")
    public String session(@RequestBody String callbackInfo, HttpServletRequest request){
        JSONObject object=JSONObject.parseObject(callbackInfo);

        return service.session(object);
    }
    @PostMapping("dvr")
    public String dvr(@RequestBody String callbackInfo, HttpServletRequest request){
        JSONObject object=JSONObject.parseObject(callbackInfo);

        return service.dvr(object);
    }
}
