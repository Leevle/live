package com.leevle.live.controller;

import com.leevle.live.feign.srsServer;
import com.leevle.live.model.Live;
import com.leevle.live.service.ControlService;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
public class feignController {
    @Resource
    private srsServer server;
    @Resource
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

    @PostMapping("/stopLive/{uuid}")
    public String stopLive(@PathVariable String uuid){
        String clientId=controlService.stopLive(uuid,false);
        return server.stopClientPushStream(clientId);
    }

    @PostMapping("/ban")
    public String banLive(String uuid,Boolean ban){
        LoggerFactory.getLogger(this.getClass()).info(uuid);
        String clientId=controlService.stopLive(uuid,ban);
        return server.stopClientPushStream(clientId);
    }


}
