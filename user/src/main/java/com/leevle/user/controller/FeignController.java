package com.leevle.user.controller;

import com.alibaba.nacos.client.config.utils.IOUtils;
import com.leevle.user.feign.LiveServer;
import com.leevle.user.service.FeignService;
import com.leevle.user.utils.ConvertUtil;
import com.leevle.user.utils.Result;
import feign.Response;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.nio.charset.StandardCharsets;

@RestController
public class FeignController {
    @Resource
    Result result;
    @Resource
    FeignService service;
    @Resource
    LiveServer liveServer;
    @GetMapping("/getOnlineLive")
    public String getOnlineLive(){
        return service.getOnlineLive();
    }

    @GetMapping("/getDvrList/{username}")
    public String getDvrList(@PathVariable String username){
        return service.getDvrList(username);
    }

    @GetMapping("/getDvr/{filename}")
    public void getDvr(HttpServletResponse httpServletResponse,@PathVariable String filename) throws IOException {
        Response response=liveServer.getDvr(filename);
        Response.Body body = response.body();
        response.headers().forEach((key,value)->{
            httpServletResponse.setHeader(key,value.stream().findFirst().get());
        });
        InputStream inputStream = body.asInputStream();
        BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream);
        BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(httpServletResponse.getOutputStream());
        int length = 0;
        byte[] temp = new byte[1024 * 10];
        while ((length = bufferedInputStream.read(temp)) != -1) {
            bufferedOutputStream.write(temp, 0, length);
        }
        bufferedOutputStream.flush();
        bufferedOutputStream.close();
        bufferedInputStream.close();
        inputStream.close();

    }
}
