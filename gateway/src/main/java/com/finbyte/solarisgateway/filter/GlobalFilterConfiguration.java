package com.finbyte.solarisgateway.filter;

import static org.springframework.cloud.gateway.support.ServerWebExchangeUtils.GATEWAY_ORIGINAL_REQUEST_URL_ATTR;
import static org.springframework.cloud.gateway.support.ServerWebExchangeUtils.GATEWAY_REQUEST_URL_ATTR;
import static org.springframework.cloud.gateway.support.ServerWebExchangeUtils.GATEWAY_ROUTE_ATTR;

import com.finbyte.solarisgateway.auth.AuthTokenService;
import com.finbyte.solarisgateway.util.SolarisGatewayConstant.EnvironmentProfileName;
import java.net.URI;
import java.util.Collections;
import java.util.Set;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.cloud.gateway.filter.ratelimit.PrincipalNameKeyResolver;
import org.springframework.cloud.gateway.route.Route;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;

/**
 * Global pre-request filter
 */
@Slf4j
@Component
public class GlobalFilterConfiguration {


  //Global pre-request filter to add Authorization key and value to header
  @Order(-2)
  @Bean
  public GlobalFilter globalAuthorizationHeaderFilter(@Qualifier("genericAuthTokenService") AuthTokenService authTokenService) {

    return (exchange, chain) -> {

      log.trace("Global filter will be execute and add the header ");
      final ServerHttpRequest request = exchange.getRequest().mutate()
          .header(HttpHeaders.AUTHORIZATION, authTokenService.getTokenWithJWTPrefix())
          .build();
      return chain.filter(exchange.mutate().request(request).build());
    };
  }

  // Global filter to log user and redirection
  @Order()
  @Profile({EnvironmentProfileName.DEV, EnvironmentProfileName.NOT_PROD})
  @Bean
  public GlobalFilter globalLoggingFilter() {
    final PrincipalNameKeyResolver principalNameKeyResolver = new PrincipalNameKeyResolver();

    return (exchange, chain) -> principalNameKeyResolver.resolve(exchange)
        .defaultIfEmpty("Unknown Principal")
        .flatMap(name -> {
          if (log.isDebugEnabled()) {
            final Set<URI> uris = exchange.getAttributeOrDefault(GATEWAY_ORIGINAL_REQUEST_URL_ATTR, Collections.emptySet());
            final String originalUri = (uris.isEmpty()) ? "Unknown" : uris.iterator().next().toString();
            final Route route = exchange.getAttribute(GATEWAY_ROUTE_ATTR);
            final String routeId = null != route ? route.getId() : "unknownId";
            final URI routeUri = exchange.getAttribute(GATEWAY_REQUEST_URL_ATTR);
            final ServerHttpRequest request = exchange.getRequest();

            log.debug(" Incoming request {} {} by user: {} is routed to uri: {} path: {} routeLocator id: {}",
                request.getMethodValue(),
                originalUri,
                name,
                routeUri,
                request.getPath(),
                routeId
            );
          }
          return chain.filter(exchange);
        });
  }
}
