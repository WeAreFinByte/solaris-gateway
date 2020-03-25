package com.finbyte.solarisgateway.filter;

import static com.finbyte.solarisgateway.util.SolarisGatewayConstant.ELAPSED_TIME_BEGIN;
import static org.springframework.cloud.gateway.support.ServerWebExchangeUtils.GATEWAY_ORIGINAL_REQUEST_URL_ATTR;
import static org.springframework.cloud.gateway.support.ServerWebExchangeUtils.GATEWAY_REQUEST_URL_ATTR;
import static org.springframework.cloud.gateway.support.ServerWebExchangeUtils.GATEWAY_ROUTE_ATTR;

import com.finbyte.solarisgateway.util.SolarisGatewayConstant.GatewayLogging;
import java.net.URI;
import java.util.Collections;
import java.util.Set;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.cloud.gateway.filter.ratelimit.PrincipalNameKeyResolver;
import org.springframework.cloud.gateway.route.Route;
import org.springframework.context.annotation.Bean;
import org.springframework.core.annotation.Order;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

/**
 * Global pre-request filter
 */
@Slf4j
@Component
public class GlobalFilterLoggingConfiguration {

  // Global filter to log user and redirection
  @Order()
  @Bean
  @ConditionalOnProperty(
      value = GatewayLogging.ENABLED_GLOBAL_LOGGING_FILTER_KEY,
      havingValue = "true")
  public GlobalFilter globalUserLoggingFilter() {
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

            log.debug(" Incoming request {} {} by module: {} is routed to uri: {} path: {} routeLocator id: {}",
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

  @Order()
  @Bean
  @ConditionalOnProperty(
      value = GatewayLogging.ENABLED_ELAPSED_TIME_LOGGING_FILTER_KEY,
      havingValue = "true")
  public GlobalFilter globalFilterElapsedGatewayFilter() {

    return (exchange, chain) -> {
      if (log.isDebugEnabled()) {
        exchange.getAttributes().put(ELAPSED_TIME_BEGIN, System.nanoTime());
      }
      return chain.filter(exchange).then(
          Mono.fromRunnable(() -> {
            if (log.isDebugEnabled()) {
              final Long startTime = exchange.getAttribute(ELAPSED_TIME_BEGIN);
              if (startTime != null) {
                log.debug("Resource server {} response time {} nanoseconds ", exchange.getRequest().getPath(), System.nanoTime() - startTime);
              }
            }
          })
      );
    };
  }
}
