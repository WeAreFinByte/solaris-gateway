package com.finleap.solarisgateway.routelocater;

import com.finleap.solarisgateway.util.SolarisGatewayConstant;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Slf4j
@Component
public class SolarisRouteLocator {

  @Resource(name = "solarisRetryGatewayFilter")
  private GatewayFilter solarisRetryGatewayFilter;

  private String uri;

  public SolarisRouteLocator(@Value("${" + SolarisGatewayConstant.RouteLocator.DEFAULT_URI_KEY + "}") String uri) {
    this.uri = uri;
  }

  @Bean
  public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
    return builder.routes()
        .route(r -> r.host("**").filters(f -> f.filters(solarisRetryGatewayFilter))
            .uri(uri)
        )
        .build();
  }
}
