package com.shopperapp.api_gateway.filters;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Component
@Slf4j
public class GlobalLoggingFilter implements GlobalFilter {
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        log.info("logging from Global Pre: {}", exchange.getRequest().getURI());
        return chain.filter(exchange).then(Mono.fromRunnable(
                () -> {
                    log.info("logging fro  Global Post: {}", exchange.getResponse().getStatusCode());
                }));
    }
}
