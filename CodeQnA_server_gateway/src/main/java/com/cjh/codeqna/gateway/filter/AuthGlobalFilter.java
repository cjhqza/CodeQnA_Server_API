package com.cjh.codeqna.gateway.filter;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.cjh.codeqna.model.vo.common.Result;
import com.cjh.codeqna.model.vo.common.ResultCodeEnum;
import com.cjh.codeqna.model.vo.user.UserBaseInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.StringUtils;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.nio.charset.StandardCharsets;
import java.util.List;

/**
 * @Author: cjh
 * @Description: 全局登录过滤器
 * @Create: 2025-03-04 15:01
 */
@Component
public class AuthGlobalFilter implements GlobalFilter, Ordered {
    @Autowired
    private RedisTemplate<String , String> redisTemplate;

    private AntPathMatcher antPathMatcher = new AntPathMatcher();

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        // 获取请求路径
        ServerHttpRequest request = exchange.getRequest();
        String path = request.getURI().getPath();

        UserBaseInfo userBaseInfo = this.getUserInfo(request);
        // 判断路径 /api/**/auth/**，登录校验
        if(antPathMatcher.match("/api/**/auth/**", path)) {
            // 登录校验
            if(userBaseInfo == null) {
                ServerHttpResponse response = exchange.getResponse();
                return out(response, ResultCodeEnum.LOGIN_AUTH);
            }
        }
        // 放行
        return chain.filter(exchange);
    }

    private UserBaseInfo getUserInfo(ServerHttpRequest request) {
        // 从请求头获取token
        String token = "";
        List<String> tokenList = request.getHeaders().get("token");
        if(tokenList != null) {
            token = tokenList.get(0);
        }
        // 判断token是否为空
        if(!StringUtils.isEmpty(token)) {
            // 根据token查询redis
            String userBaseInfoJSON = redisTemplate.opsForValue().get("user:login:"+token);
            if(StringUtils.isEmpty(userBaseInfoJSON)) {
                return null ;
            }else {
                return JSON.parseObject(userBaseInfoJSON , UserBaseInfo.class) ;
            }
        }
        return null;
    }

    private Mono<Void> out(ServerHttpResponse response, ResultCodeEnum resultCodeEnum) {
        Result result = Result.build(null, resultCodeEnum);
        byte[] bits = JSONObject.toJSONString(result).getBytes(StandardCharsets.UTF_8);
        DataBuffer buffer = response.bufferFactory().wrap(bits);
        //指定编码，否则在浏览器中会中文乱码
        response.getHeaders().add("Content-Type", "application/json;charset=UTF-8");
        return response.writeWith(Mono.just(buffer));
    }

    @Override
    public int getOrder() {
        return 0;
    }
}
