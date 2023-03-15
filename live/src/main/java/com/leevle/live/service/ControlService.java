package com.leevle.live.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.leevle.live.mapper.LiveMapper;
import com.leevle.live.model.Live;
import com.leevle.live.utils.Utils;
import com.leevle.live.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ControlService {
    @Autowired
    Result result;
    @Autowired
    LiveMapper liveMapper;
    public String regisetr(Live live){
        QueryWrapper<Live> liveQueryWrapper=new QueryWrapper<>();
        liveQueryWrapper.eq("uuid",live.getUuid());

        if(liveMapper.selectCount(liveQueryWrapper)==0){
            live.setPushCode(Utils.generatorPushCode());
            liveMapper.insert(live);
            result.setData("注册成功");
        }
        else
        {
            result.setCode(101);
            result.setData("重复的uuid");
        }
        return result.toString();
    }

    public String stopLive(Live live,Boolean stop){
        QueryWrapper<Live> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("uuid",live.getUuid());
        Live liveR=liveMapper.selectOne(queryWrapper);
        liveR.setBan(live.getBan());
        String clientId="";
        if (liveR!=null && liveR.getClientId()!=null && stop){
            clientId=liveR.getClientId();
            liveR.setClientId("");
            liveR.setStreamId("");
        }
        liveMapper.update(liveR,queryWrapper);
        return clientId;
    }


    public String updatePushCode(Live live) {
        QueryWrapper<Live> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("uuid",live.getUuid());
        Live liveR=liveMapper.selectOne(queryWrapper);
        if (liveR!=null && liveR.getClientId()==null){
            liveR.setPushCode(Utils.generatorPushCode());
            liveMapper.update(liveR,queryWrapper);
        }
        else{
            result.setCode(102);
            result.setMessage(live==null?"不存在的uuid":"当前用户直播未结束，不能更新");
        }
        return result.toString();
    }
}
