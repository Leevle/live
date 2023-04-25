package com.leevle.live.controller;

import com.leevle.live.config.MedioHttpRequestHandler;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@RestController
public class DvrController {
    @Value("${record.path}")
    private String url;

    @Resource
    private MedioHttpRequestHandler medioHttpRequestHandler;


    @GetMapping("/getDvrList/{uuid}")
    public String getDvrList(@PathVariable String uuid){
        File f = new File(url);//获取路径  F:\测试目录
        if (!f.exists()) {
            System.out.println(url + " not exists");//不存在就输出
            return null;
        }

        File fa[] = f.listFiles();//用数组接收  F:\笔记总结\C#, F:\笔记总结\if语句.txt
        for (int i = 0; i < fa.length; i++) {//循环遍历
            File fs = fa[i];//获取数组中的第i个
            if (fs.isDirectory()) {
                System.out.println(fs.getName() + " [目录]");//如果是目录就输出
            } else {
                System.out.println(fs.getName());//否则直接输出
            }
        }
        return null;
    }

    @GetMapping("/getDvr/{filename}")
    public void getPlayResource(HttpServletRequest request, HttpServletResponse response, @PathVariable String filename) throws Exception {
        Path path = Paths.get(url + filename);
        if (Files.exists(path)) {
            String mimeType = Files.probeContentType(path);
            if (!StringUtils.isEmpty(mimeType)) {
                response.setContentType(mimeType);
            }
            request.setAttribute(MedioHttpRequestHandler.ATTR_FILE, path);
            medioHttpRequestHandler.handleRequest(request, response);
        } else {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            response.setCharacterEncoding(StandardCharsets.UTF_8.toString());
        }
    }

}
