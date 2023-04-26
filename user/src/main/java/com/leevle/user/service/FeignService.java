package com.leevle.user.service;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.leevle.user.feign.LiveServer;
import com.leevle.user.mapper.UserMapper;
import com.leevle.user.model.User;
import com.leevle.user.utils.Result;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service
public class FeignService {
    @Resource
    Result result;
    @Resource
    LiveServer liveServer;
    @Resource
    UserMapper userMapper;
    public String getOnlineLive(){
        JSONObject jsonObject=JSONObject.parseObject(liveServer.getOnlineLive());
        String lives=jsonObject.get("data").toString();
        QueryWrapper<User> userQueryWrapper=new QueryWrapper<>();
        jsonObject=JSONObject.parseObject(lives);
        List<Map<String,String>> res= (List<Map<String, String>>) jsonObject.get("lives");
        for (int i=0;i<res.size();i++)
        {
            String uuid=res.get(i).get("uuid");
            userQueryWrapper.eq("uuid",uuid);
            String username=userMapper.selectOne(userQueryWrapper).getUsername();
            res.get(i).put("username",username);
            res.get(i).remove("uuid");
        }
        return res.toString();
    }

    public String getDvrList(String username){
        QueryWrapper<User> userQueryWrapper=new QueryWrapper<>();
        userQueryWrapper.eq("username",username);
        User user=userMapper.selectOne(userQueryWrapper);
        if(user!=null){
            result=JSONObject.parseObject(liveServer.getDvrList(user.getUuid()),Result.class);
        return result.getData().get("files").toString();}
        else {
            result.setCode(0);
            result.setMessage("用户不存在");
            return result.toString();
        }
    }
}
