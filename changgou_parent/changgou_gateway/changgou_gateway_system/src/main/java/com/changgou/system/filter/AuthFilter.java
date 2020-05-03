package com.changgou.system.filter;

import com.changgou.system.utils.JWTUtil;
import io.jsonwebtoken.Claims;
import org.apache.commons.lang.StringUtils;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * @author chayedan666
 * @version 1.0
 * @className: AuthFilter
 * @description: 鉴权
 * @date: 2020/5/3
 */
@Component
public class AuthFilter implements GlobalFilter, Ordered {
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        // 获取请求对象
        ServerHttpRequest request = exchange.getRequest();
        // 获取响应对象
        ServerHttpResponse response = exchange.getResponse();
        // 从请求对象中获取访问者的url地址
        String uri = request.getURI().getPath();
        // 判断是否是登录或者注册，是则放行
        if (uri.contains("admin/login")){
            return chain.filter(exchange);
        }
        // 从请求对象中获取令牌
        String token = request.getHeaders().getFirst("token");
        // 检查是否有令牌，没有则报错
        if (StringUtils.isEmpty(token)){
            response.setStatusCode(HttpStatus.UNAUTHORIZED);
            System.out.println("没有Token");
            return response.setComplete();
        }
        try {
            // 获取令牌后解析
            JWTUtil.parseJWT(token);
            // 正确，放行
            return chain.filter(exchange);
        } catch (Exception e) {
            //错误，返回错误信息
            e.printStackTrace();
            response.setStatusCode(HttpStatus.UNAUTHORIZED);
            System.out.println("登录无效");
            return response.setComplete();
        }

    }

    @Override
    public int getOrder() {
        return 2;
    }
}
