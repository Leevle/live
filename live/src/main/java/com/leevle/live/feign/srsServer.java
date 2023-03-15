package com.leevle.live.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(url = "http://live.com:1985/",value = "srs")
public interface srsServer {
    @GetMapping("api/v1/versions")
    String getVersion();
    @GetMapping("api/v1/streams")
    String getStreams(@RequestParam(required = false) String start,@RequestParam(required = false) String count);
    @GetMapping("api/v1/clients")
    String getClients(@RequestParam(required = false) String start,@RequestParam(required = false) String count);
    @GetMapping("api/v1/streams/{id}")
    String getSingularStream(@PathVariable String id);
    @GetMapping("api/v1/streams/{id}")
    String getSingularClient(@PathVariable String id);
    @DeleteMapping("api/v1/clients/{id}")
    String stopClientPushStream(@PathVariable String id);

}
