package com.finleap.solarisgateway.filter;

import com.finleap.solarisgateway.util.SolarisGatewayConstant;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.core.annotation.Order;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class FilterConfiguration {

    @Order(1)
    @Bean
    public GlobalFilter globalFilter() {

        return (exchange, chain) -> {

            log.debug("Global filter will be execute and add the header ");
            final ServerHttpRequest request = exchange.getRequest().mutate().header(SolarisGatewayConstant.Gateway.SECURITY_HEADER_KEY, Math.random() * 10 + "test").build();
            return chain.filter(exchange.mutate().request(request).build());
        };
    }
}
