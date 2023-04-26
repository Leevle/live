package com.leevle.user.feign;

import feign.Response;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;


@FeignClient(name = "live")
public interface LiveServer {
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

    @GetMapping("/getOnlineLive")
    String getOnlineLive();

    @GetMapping("/getDvrList/{uuid}")
    String getDvrList(@PathVariable String uuid);

    @GetMapping("/getDvr/{filename}")
    Response getDvr(@PathVariable String filename);

}
