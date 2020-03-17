package com.finleap.solarisgateway.filter;

import com.finleap.solarisgateway.auth.AuthTokenService;
import javax.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;

/**
 * Global pre-request filter to add Authorization key and value to header
 */
@Slf4j
@Component
public class GlobalFilterConfiguration {

  @Resource(name = "genericAuthTokenService")
  private AuthTokenService authTokenService;

  @Order(-1)
  @Bean
  public GlobalFilter globalAuthorizationHeaderFilter() {

    return (exchange, chain) -> {

      log.debug("Global filter will be execute and add the header ");
      final ServerHttpRequest request = exchange.getRequest().mutate()
          .header(HttpHeaders.AUTHORIZATION, authTokenService.getTokenWithJWTPrefix())
          .build();
      return chain.filter(exchange.mutate().request(request).build());
    };
  }
}
