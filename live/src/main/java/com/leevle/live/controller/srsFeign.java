package com.leevle.live.controller;

import com.leevle.live.feign.srsServer;
import com.leevle.live.mapper.LiveMapper;
import com.leevle.live.model.Live;
import com.leevle.live.service.ControlService;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class srsFeign {
    @Autowired
    private srsServer server;
    @Autowired
    LiveMapper liveMapper;
    @Autowired
    ControlService controlService;
    @GetMapping("srs/getVersion")
    public String getSrsVersion(){
        return server.getVersion();
    }
    @GetMapping("srs/getStreams")
    public String getSrsStream(@RequestParam String start,@RequestParam String end){
        return server.getStreams(start,end);
    }
    @GetMapping("srs/getClients")
    public String getSrsClients(@RequestParam String start,@RequestParam String end){
        return server.getClients(start,end);
    }
    @GetMapping("srs/getSingularStream/{streamId}")
    public String getSrsStream(@PathVariable String streamId){
        return server.getSingularStream(streamId);
    }
    @GetMapping("srs/getSingularClient/{clientId}")
    public String getSrsClient(@PathVariable String clientId){
        return server.getSingularClient(clientId);
    }

    //权限验证:管理员，普通用户
    @PostMapping("stopLive")
    public String stopLive(Live live){
        String clientId=controlService.stopLive(live,true);
        LoggerFactory.getLogger(this.getClass()).info(clientId);
        return server.stopClientPushStream(clientId);
    }

    //权限验证:管理员
    @PostMapping("ban")
    public String banLive(Live live){
        String clientId=controlService.stopLive(live,live.getBan());
        LoggerFactory.getLogger(this.getClass()).info(clientId);
        return server.stopClientPushStream(clientId);
    }
}
