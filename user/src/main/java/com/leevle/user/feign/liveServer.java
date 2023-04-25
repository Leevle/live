package com.leevle.user.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;


@FeignClient(name = "live")
public interface liveServer {
    @PostMapping("/register/{uuid}")
    String register(@PathVariable String uuid);

    @GetMapping("/getPushUrl/{uuid}")
    String getPushUrl(@PathVariable String uuid);

    @GetMapping("/updatePushToken/{uuid}")
    String updatePushToken(@PathVariable String uuid);

    @PostMapping("/stopLive/{uuid}")
    String stopLive(@PathVariable String uuid);

    @PostMapping("/ban")
    String ban(@RequestParam String uuid,@RequestParam Boolean ban);
}
