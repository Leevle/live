package com.leevle.live.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.leevle.live.config.MedioHttpRequestHandler;
import com.leevle.live.mapper.LiveMapper;
import com.leevle.live.model.Live;
import com.leevle.live.utils.Result;
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
import java.util.ArrayList;
import java.util.List;

@RestController
public class DvrController {
    @Value("${record.path}")
    private String url;
    @Resource
    Result result;
    @Resource
    private MedioHttpRequestHandler medioHttpRequestHandler;
    @Resource
    LiveMapper liveMapper;

    @GetMapping("/getDvrList/{uuid}")
    public String getDvrList(@PathVariable String uuid){
        QueryWrapper<Live> liveQueryWrapper=new QueryWrapper<>();
        liveQueryWrapper.eq("uuid",uuid);
        Live liveR=liveMapper.selectOne(liveQueryWrapper);
        if(liveR!=null) {
            File f = new File(url);
            if (!f.exists()) {
                return null;
            }
            List<String> list = new ArrayList<>();
            File fa[] = f.listFiles();
            for (int i = 0; i < fa.length; i++) {
                File fs = fa[i];
                if (fs.isFile()) {
                    String name = fs.getName();
                    if (name.contains(liveR.getPushCode()))
                        list.add(name);
                }
            }
            JSONObject object=new JSONObject();
            object.put("files",list);
            result.setMessage("文件列表");
            result.setData(object);

        }
        else
            result.setCode(1);
        return result.toString();
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
