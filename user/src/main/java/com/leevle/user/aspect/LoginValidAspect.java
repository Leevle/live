package com.leevle.user.aspect;

import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.leevle.user.utils.JWTUtils;
import com.leevle.user.utils.Result;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@Aspect
@Component
public class LoginValidAspect {
    @Resource
    Result result;
    @Around(value ="execution(public * com.leevle.user.controller.UserController.*(..))")
    public String before(ProceedingJoinPoint point ) throws Throwable{
        Object[] objs = point.getArgs();
        HttpServletRequest request= (HttpServletRequest) objs[0];
        String token=request.getHeader("token");
        try {
            DecodedJWT res=JWTUtils.decode(token);
            request.setAttribute("uuid",res.getClaim("uuid").asString());
            return (String) point.proceed();
        }
        catch (JWTDecodeException e)
        {
            result.setMessage("token失效");
            return result.toString();
        }

    }
}
