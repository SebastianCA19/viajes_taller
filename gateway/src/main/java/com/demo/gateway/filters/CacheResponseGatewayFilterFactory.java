package com.demo.gateway.filters;

import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.data.redis.core.ReactiveStringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Component
public class CacheResponseGatewayFilterFactory
        extends AbstractGatewayFilterFactory<CacheResponseGatewayFilterFactory.Config> {

    private final ReactiveStringRedisTemplate redisTemplate;

    public CacheResponseGatewayFilterFactory(ReactiveStringRedisTemplate redisTemplate) {
        super(Config.class);
        this.redisTemplate = redisTemplate;
    }

    @Override
    public GatewayFilter apply(Config config) {
        return (exchange, chain) -> {
            // Solo cacheamos GET
            if (!"GET".equalsIgnoreCase(String.valueOf(exchange.getRequest().getMethod()))) {
                return chain.filter(exchange);
            }

            String key = config.getPrefix() + exchange.getRequest().getURI().getPath();

            return redisTemplate.opsForValue().get(key)
                    .flatMap(cachedResponse -> {
                        // Si ya hay cache -> responder directo
                        exchange.getResponse().getHeaders().add("X-Cache", "HIT");
                        byte[] bytes = cachedResponse.getBytes();
                        return exchange.getResponse().writeWith(Mono.just(exchange.getResponse()
                                .bufferFactory().wrap(bytes)));
                    })
                    .switchIfEmpty(
                            // Si no hay cache -> procesar y guardar
                            chain.filter(exchange).then(Mono.defer(() -> {
                                exchange.getResponse().getHeaders().add("X-Cache", "MISS");
                                // (Podrías interceptar el body y guardarlo aquí)
                                return Mono.empty();
                            }))
                    );
        };
    }

    public static class Config {
        private String prefix;

        public String getPrefix() {
            return prefix;
        }

        public void setPrefix(String prefix) {
            this.prefix = prefix;
        }
    }
}


