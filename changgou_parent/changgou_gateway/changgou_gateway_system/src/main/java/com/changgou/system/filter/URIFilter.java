package com.changgou.system.filter;

import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * @author chayedan666
 * @version 1.0
 * @className: URIFilter
 * @description: 记录访问者的URL地址
 * @date: 2020/5/2
 */
@Component
public class URIFilter implements GlobalFilter, Ordered {
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        String path = exchange.getRequest().getURI().getPath();
        System.out.println("====URL===="+path);
        return chain.filter(exchange);
    }

    @Override
    public int getOrder() {
        return 1;
    }
}
