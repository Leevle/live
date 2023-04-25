package com.leevle.live.service;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.leevle.live.mapper.LiveMapper;
import com.leevle.live.model.Live;
import com.leevle.live.utils.Result;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ControlService {
    @Autowired
    Result result;
    @Autowired
    LiveMapper liveMapper;


    public String stopLive(String uuid,Boolean ban){
        QueryWrapper<Live> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("uuid",uuid);
        Live liveR=liveMapper.selectOne(queryWrapper);
        if (liveR!=null){

            liveR.setBan(ban);
        }
        String clientId="";
        if (liveR!=null && liveR.getClientId()!=null){
            clientId=liveR.getClientId();
            liveR.setClientId("");
            liveR.setStreamId("");
            liveMapper.update(liveR,queryWrapper);
        }
        return clientId;
    }


    public String updatePushCode(String uuid) {
        QueryWrapper<Live> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("uuid",uuid);
        Live liveR=liveMapper.selectOne(queryWrapper);

        if (liveR!=null && liveR.getClientId().equals("")){
            //liveR.setPushCode(UUID.randomUUID().toString().replace("-","").substring(0,8));
            liveR.setPushToken(UUID.randomUUID().toString().replace("-",""));
            liveMapper.update(liveR,queryWrapper);
        }
        else{
            result.setCode(1);
            result.setMessage(liveR==null?"该uuid未开通直播服务":"当前用户直播未结束，不能更新");
        }
        return result.toString();
    }

    public String register(String uuid){
        QueryWrapper<Live> liveQueryWrapper=new QueryWrapper<>();
        liveQueryWrapper.eq("uuid",uuid);
        Live live=new Live();
        if(liveMapper.selectCount(liveQueryWrapper)==0){
            live.setPushCode(UUID.randomUUID().toString().replace("-","").substring(0,8));
            live.setPushToken(UUID.randomUUID().toString().replace("-",""));
            live.setUuid(uuid);
            live.setBan(false);
            LoggerFactory.getLogger(this.getClass()).info(live.toString());
            liveMapper.insert(live);
            result.setMessage("注册成功");
        }
        else
        {
            result.setCode(1);
            result.setMessage("重复的uuid");
        }
        return result.toString();
    }
    public String getPushCode(String uuid,boolean token){
        QueryWrapper<Live> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("uuid",uuid);

        Live liveR=liveMapper.selectOne(queryWrapper);
        if (liveR!=null){
            JSONObject object=new JSONObject();
            object.put(token?"push_code":"pull_code",liveR.getPushCode()+(token?("?token="+liveR.getPushToken()):""));
            result.setData(object);
        }
        else {
            result.setCode(1);
            result.setMessage("uuid不存在");
        }

        return result.toString();
    }
}
