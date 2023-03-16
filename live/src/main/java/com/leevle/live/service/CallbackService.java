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
import java.util.HashMap;
import java.util.Map;

@Service
public class CallbackService {
    @Autowired
    Result result;
    @Autowired
    LiveMapper liveMapper;
    public String stream(JSONObject object){


        String stream_url=object.getString("stream");
        String client_id=object.getString("client_id");
        String stream_id=object.getString("stream_id");
        String action=object.getString("action");
        String param=object.getString("param");

        String[] params =param.substring(1).split("&");

        if (!(params.length==1&& params[0].startsWith("token"))){
            result.setCode(2);
            return result.toString();
        }

        QueryWrapper<Live> liveQueryWrapper=new QueryWrapper<>();
        Map<String,String> map=new HashMap<>();
        map.put("push_code",stream_url);
        map.put("push_token",params[0].substring(6));

        liveQueryWrapper.allEq(map);


        Live live=liveMapper.selectOne(liveQueryWrapper);
        if(live!=null && !live.getBan())
        {

            live.setClientId(action.equals("on_publish")?client_id:"");
            live.setStreamId(action.equals("on_publish")?stream_id:"");
            liveMapper.update(live,liveQueryWrapper);
        }
        else
            result.setCode(2);
        return result.toString();
    }

    public String session(JSONObject object){

        LoggerFactory.getLogger(this.getClass()).info(object.toString());
        return result.toString();
    }

}
