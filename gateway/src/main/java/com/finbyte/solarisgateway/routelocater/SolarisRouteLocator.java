package com.finbyte.solarisgateway.routelocater;

import com.finbyte.solarisgateway.util.SolarisGatewayConstant;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Slf4j
@Component("solarisRouteLocator")
public class SolarisRouteLocator {

  public final static String ROUTER_ID = "solarisgateway.main";

  @Resource(name = "solarisRetryGatewayFilter")
  private GatewayFilter solarisRetryGatewayFilter;

  @Getter
  private String uri;

  @Getter
  private String prefix;

  @Getter
  private String targetPrefix;

  public SolarisRouteLocator(@Value("${" + SolarisGatewayConstant.RouteLocator.DEFAULT_URI_KEY + "}") String uri,
      @Value("${" + SolarisGatewayConstant.RouteLocator.DEFAULT_PREFIX_KEY + "}") String prefix,
      @Value("${" + SolarisGatewayConstant.RouteLocator.DEFAULT_TARGET_PREFIX_KEY + "}") String targetPrefix) {
    this.uri = uri;
    this.prefix = prefix;
    this.targetPrefix = targetPrefix;
  }

  @Bean
  public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
    return builder.routes()
        .route(ROUTER_ID, r -> r.path(prefix + "**") //
            .filters(f -> f.rewritePath(prefix + "(?<remains>.*)", targetPrefix + "${remains}") //
                .filter(solarisRetryGatewayFilter)) //
            .uri(uri))
        .build();
  }
}
