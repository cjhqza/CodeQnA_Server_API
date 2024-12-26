package com.cjh.codeqna.manager.interceptor;

import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSON;
import com.cjh.codeqna.model.entity.system.SysUser;
import com.cjh.codeqna.model.vo.common.Result;
import com.cjh.codeqna.model.vo.common.ResultCodeEnum;
import com.cjh.codeqna.util.AuthContextUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.concurrent.TimeUnit;

/**
 * @Author: cjh
 * @Description: 登录权限拦截器
 * @Create: 2024-12-26 15:25
 */
@Component
public class LoginAuthInterceptor implements HandlerInterceptor {
    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 获取请求方式
        // 如果请求方式是options 预检请求，直接放行
        String method = request.getMethod();
        if ("OPTIONS".equals(method)) {
            return true;
        }
        // 从请求头获取token
        String token = request.getHeader("token");
        // 如果token为空，返回错误提示
        if (StrUtil.isEmpty(token)) {
            responseNoLoginInfo(response);
            return false;
        }
        // 如果token不为空，拿着token查询redis
        String managerUserInfoString = redisTemplate.opsForValue().get("manager-user:login" + token);
        // 如果redis查询不到数据，返回错误提示
        if (StrUtil.isEmpty(managerUserInfoString)) {
            responseNoLoginInfo(response);
            return false;
        }
        // 如果redis查询到管理员用户信息，把管理员用户信息放到ThreadLocal里面
        SysUser sysUser = JSON.parseObject(managerUserInfoString, SysUser.class);
        AuthContextUtil.set(sysUser);
        // 把redis管理员用户信息数据将过期时间延期，多增30分钟
        redisTemplate.expire("manager-user:login" + token, 30, TimeUnit.MINUTES);
        // 放行
        return true;
    }
    //响应208状态码给前端
    private void responseNoLoginInfo(HttpServletResponse response) {
        Result<Object> result = Result.build(null, ResultCodeEnum.LOGIN_AUTH);
        PrintWriter writer = null;
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=utf-8");
        try {
            writer = response.getWriter();
            writer.print(JSON.toJSONString(result));
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (writer != null) writer.close();
        }
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        // ThreadLocal删除
        AuthContextUtil.remove();
    }
}
