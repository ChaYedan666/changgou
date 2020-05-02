package com.changgou.system.filter;

import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * @author chayedan666
 * @version 1.0
 * @className: IpFilter
 * @description:
 * @date: 2020/5/2
 */
@Component
public class IpFilter implements GlobalFilter, Ordered {
    /**
     * 记录访问者的IP地址
     * @param exchange
     * @param chain
     * @return
     */
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        // 获取请求
        ServerHttpRequest request = exchange.getRequest();
        String hostName = request.getRemoteAddress().getHostName();
        System.out.println("====ip===="+hostName);
        // 过滤后放回去
        return chain.filter(exchange);
    }

    @Override
    public int getOrder() {
        return 0;
    }
}
