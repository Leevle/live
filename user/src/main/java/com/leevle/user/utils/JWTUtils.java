package com.leevle.user.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;

import java.util.Calendar;
import java.util.Map;

public class JWTUtils {
    // 签名密钥
    private static final String SECRET = "W6N!=F#k*T_Dghs-Rq6ADX+jR%$Kj9l3*3nXx@LwnDBnCZ8InFcP9o+Mptbcg!*7-XjUhaZ2SnqCccoz3CI^DPA2j-FUzYcJ9E%euLK+6Rp6XEP+H$lL76r=9zZv4Z@F7w-O_lp&RDB3vqh!hpD7EGzaLCieXxIHzwhh3ko=s@b!MFo!Q-kQMt8=5Hq$TWM0RiQg@tFD";

    /**
     * 生成token
     * @param payload token携带的信息
     * @return token字符串
     */
    public static String getToken(Map<String,String> payload){
        // 指定token过期时间为1天
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, 7);

        JWTCreator.Builder builder = JWT.create();
        // 构建payload
        payload.forEach(builder::withClaim);
        // 指定过期时间和签名算法
        return builder.withExpiresAt(calendar.getTime()).sign(Algorithm.HMAC256(SECRET));
    }


    /**
     * 解析token
     * @param token token字符串
     * @return 解析后的token
     */
    public static DecodedJWT decode(String token){
        JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256(SECRET)).build();
        return jwtVerifier.verify(token);
    }
}

