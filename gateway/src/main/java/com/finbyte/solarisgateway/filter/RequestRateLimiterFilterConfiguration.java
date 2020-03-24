package com.finbyte.solarisgateway.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.OrderedGatewayFilter;
import org.springframework.cloud.gateway.filter.factory.RequestRateLimiterGatewayFilterFactory;
import org.springframework.cloud.gateway.filter.ratelimit.KeyResolver;
import org.springframework.cloud.gateway.filter.ratelimit.PrincipalNameKeyResolver;
import org.springframework.cloud.gateway.filter.ratelimit.RedisRateLimiter;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class RequestRateLimiterFilterConfiguration {

  protected static int FILTER_ORDER = -1;

  @Bean("solarisRateLimiterGatewayFilter")
  public GatewayFilter solarisRateLimiterGatewayFilter(RequestRateLimiterGatewayFilterFactory requestRateLimiterGatewayFilterFactory) {
    return new OrderedGatewayFilter(requestRateLimiterGatewayFilterFactory.apply(getConfig())
        , FILTER_ORDER);
  }

  @Bean(value = PrincipalNameKeyResolver.BEAN_NAME)
  public KeyResolver principalNameKeyResolver() {
    return new PrincipalNameKeyResolver();
  }

  @Bean
  public RedisRateLimiter redisRateLimiter() {
    return new RedisRateLimiter(1, 2);
  }

  // Configuration
  protected RequestRateLimiterGatewayFilterFactory.Config getConfig() {

    return new RequestRateLimiterGatewayFilterFactory.Config();
  }
}