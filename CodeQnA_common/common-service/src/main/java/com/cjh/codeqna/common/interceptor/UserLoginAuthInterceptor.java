package com.cjh.codeqna.common.interceptor;

import com.alibaba.fastjson2.JSON;
import com.cjh.codeqna.model.vo.user.UserBaseInfo;
import com.cjh.codeqna.util.AuthContextUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.servlet.HandlerInterceptor;

/**
 * @Author: cjh
 * @Description: 用户登录权限拦截器
 * @Create: 2025-03-04 15:15
 */
public class UserLoginAuthInterceptor implements HandlerInterceptor {
    @Autowired
    private RedisTemplate<String , String> redisTemplate ;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        // 如果token不为空，那么此时验证token的合法性
        String userBaseInfoJSON = redisTemplate.opsForValue().get("user:login:" + request.getHeader("token"));
        // 放到ThreadLocal里面
        AuthContextUtil.setUserInfo(JSON.parseObject(userBaseInfoJSON , UserBaseInfo.class));
        return true ;

    }
}
