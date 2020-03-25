package com.finbyte.solarisgateway.filter;

import com.finbyte.solarisgateway.auth.TokenService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Component
@Slf4j
public class GlobalAuthorizationHeaderFilterConfiguration {

  //Global pre-request filter to add Authorization key and value to header
  @Order(-2)
  @Bean
  public GlobalFilter globalAuthorizationHeaderFilter(final @Qualifier("genericTokenService") TokenService tokenService) {

    return (exchange, chain) -> {
      log.trace("Global filter will be execute and add the header ");

      if (HttpStatus.UNAUTHORIZED == exchange.getResponse().getStatusCode()) {
        if (log.isDebugEnabled()) {
          log.debug("Request will be retried with new token");
        }

        return tokenService.refreshToken().flatMap(token -> addAuthorizationHeader(tokenService, exchange, chain));
      } else {
        return addAuthorizationHeader(tokenService, exchange, chain);
      }
    };
  }

  private Mono<Void> addAuthorizationHeader(final TokenService tokenService,
      ServerWebExchange exchange, GatewayFilterChain chain) {
    final String token = tokenService.getTokenWithJWTPrefix();
    if (log.isTraceEnabled()) {
      log.trace("Token {} " + token);
    }
    final ServerHttpRequest request = exchange.getRequest().mutate()
        .header(HttpHeaders.AUTHORIZATION, token)
        .build();

    return chain.filter(exchange.mutate().request(request).build());
  }
}
