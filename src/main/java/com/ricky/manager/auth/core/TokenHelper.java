package com.ricky.manager.auth.core;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.ricky.manager.auth.interceptor.BaseContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import java.util.concurrent.TimeUnit;

/**
 * 基于Redis的简单Token manager 实现
 * @author xjsh
 */
@Component
public class TokenHelper {

    private final static String TOKEN_INFO = "TOKEN:";

    /** Token过期时间, 单位：分钟 */
    private final static int TOKEN_EXPIRE = 60;

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    public void saveToken(BaseContext context) {
        if (!ObjectUtils.isEmpty(context)) {
            redisTemplate.opsForValue().set(TOKEN_INFO + context.getToken(), context, TOKEN_EXPIRE, TimeUnit.MINUTES);
        }
    }

    public void removeToken(String account, String token) {
        if (StringUtils.hasText(account)){
            redisTemplate.delete(TOKEN_INFO + token);
        }
    }

    public BaseContext getInfoFromToken(String token) throws NonLoginException {
        BaseContext context = (BaseContext)redisTemplate.opsForValue().get(TOKEN_INFO + token);
        if (context == null) {
            throw new NonLoginException("用户未登录");
        }

        // 重新设置过期时间
        redisTemplate.expire(TOKEN_INFO + token, TOKEN_EXPIRE, TimeUnit.MINUTES);
        return context;
    }

}
