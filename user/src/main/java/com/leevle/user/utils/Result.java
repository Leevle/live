package com.leevle.user.utils;

import com.alibaba.fastjson.JSONObject;
import lombok.Data;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.RequestScope;

import java.io.Serializable;

@Data
@Service
@RequestScope
public class Result implements Serializable {
    private int code;
    private String message;
    private JSONObject data;
    public Result(){
        this.code=0;
    }
    @Override
    public String toString(){
        return JSONObject.toJSONString(this);
    }
}
