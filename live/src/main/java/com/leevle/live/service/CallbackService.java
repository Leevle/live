package com.leevle.live.service;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.leevle.live.mapper.LiveMapper;
import com.leevle.live.model.Live;
import com.leevle.live.utils.Result;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class CallbackService {
    @Autowired
    Result result;
    @Autowired
    LiveMapper liveMapper;
    public String stream(JSONObject object){


        String stream_url=object.getString("stream_url");
        String client_id=object.getString("client_id");
        String stream_id=object.getString("stream_id");
        String action=object.getString("action");

        String streams[]=stream_url.split("/");

        result.setCode(streams.length==3 && streams[1].equals("livestream") ?0:202);

        QueryWrapper<Live> liveQueryWrapper=new QueryWrapper<>();
        liveQueryWrapper.eq("push_code",streams[2]);

        Live live=liveMapper.selectOne(liveQueryWrapper);
        if(live!=null && !live.getBan())
        {

            live.setClientId(action.equals("on_publish")?client_id:"");
            live.setStreamId(action.equals("on_publish")?stream_id:"");
            liveMapper.update(live,liveQueryWrapper);
        }
        else
            result.setCode(201);


        return result.toString();
    }

    public String session(JSONObject object){

        LoggerFactory.getLogger(this.getClass()).info(object.toString());
        return result.toString();
    }

}
