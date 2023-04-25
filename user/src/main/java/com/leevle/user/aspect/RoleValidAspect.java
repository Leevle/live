package com.leevle.user.aspect;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.leevle.user.mapper.UserMapper;
import com.leevle.user.model.User;
import com.leevle.user.utils.Result;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@Aspect
@Component
@Order(2)
public class RoleValidAspect {
    @Resource
    UserMapper userMapper;
    @Resource
    Result result;
    @Around(value ="execution(public * com.leevle.user.controller.UserController.Admin*(..))")
    public String before(ProceedingJoinPoint point ) throws Throwable{
        Object[] objs = point.getArgs();
        HttpServletRequest request= (HttpServletRequest) objs[0];
        String uuid=request.getAttribute("uuid").toString();
        QueryWrapper<User> userQueryWrapper=new QueryWrapper<>();

        userQueryWrapper.eq("uuid",uuid);
        User user=userMapper.selectOne(userQueryWrapper);
        if(user!=null && user.getRole())
            return (String) point.proceed();
        else
        {
            result.setMessage("权限不足");
            return result.toString();
        }
    }
}
