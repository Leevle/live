package com.leevle.user.service;


import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.leevle.user.feign.liveServer;
import com.leevle.user.mapper.UserMapper;
import com.leevle.user.utils.JWTUtils;
import com.leevle.user.utils.Result;
import com.leevle.user.model.User;
import org.apache.commons.codec.digest.DigestUtils;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
public class LoginService {
    @Resource
    UserMapper userMapper;
    @Resource
    liveServer liveServer;
    @Resource
    Result result;
    public String register(User user){

        //权限为普通用户
        user.setRole(false);
        //设置用户uuid
        user.setUuid(UUID.randomUUID().toString());
        //对密码加密
        user.setPassword(DigestUtils.md5Hex(user.getPassword()));

        try {
            if(userMapper.insert(user)==1) {
                result.setMessage("注册成功");
                liveServer.register(user.getUuid());
            }

            else
                return this.register(user);
        }
        catch (DuplicateKeyException e){
            result.setMessage("重复的用户名");
        }

        return result.toString();
    }

    public String login(User user){
        Map<String,String> loginInfo=new HashMap<>();
        QueryWrapper<User> userQueryWrapper=new QueryWrapper<>();
        Map<String,String> token=new HashMap<>();
        loginInfo.put("username",user.getUsername());
        loginInfo.put("password",DigestUtils.md5Hex(user.getPassword()));

        userQueryWrapper.allEq(loginInfo);
        user=userMapper.selectOne(userQueryWrapper);
        if(user!=null){
            result.setMessage("登录成功");
            token.put("uuid",user.getUuid());
            JSONObject data=new JSONObject();
            data.put("token", JWTUtils.getToken(token));
            result.setData(data);
        }
        else {
            result.setCode(1);
            result.setMessage("用户名或密码错误");
        }
        return result.toString();
    }


}
