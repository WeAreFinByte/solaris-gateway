package com.finbyte.solarisgateway.filter;

import com.finbyte.solarisgateway.routelocater.SolarisRouteLocator;
import com.finbyte.solarisgateway.util.properties.RetryProperties;
import java.time.Duration;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.OrderedGatewayFilter;
import org.springframework.cloud.gateway.filter.factory.RetryGatewayFilterFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class RetryFilterConfiguration {

  protected static int FILTER_ORDER = -1;

  @Bean("solarisRetryGatewayFilter")
  public GatewayFilter solarisRetryGatewayFilter(final RetryGatewayFilterFactory retryGatewayFilterFactory, final RetryProperties retryProperties) {
    return new OrderedGatewayFilter(retryGatewayFilterFactory.apply(getRetryConfig(retryProperties))
        , FILTER_ORDER);
  }

  // Retry Configuration
  protected RetryGatewayFilterFactory.RetryConfig getRetryConfig(RetryProperties retryProperties) {
    final RetryGatewayFilterFactory.RetryConfig retryConfig = new RetryGatewayFilterFactory.RetryConfig();
    retryConfig.setRouteId(SolarisRouteLocator.ROUTER_ID);

    retryConfig
        .setMethods(retryProperties.getHttpMethods())
        .setRetries(retryProperties.getRetries())
        .setStatuses(retryProperties.getHttpHttpStatuses())
        .setBackoff(Duration.ofMillis(retryProperties.getFirstBackoff()), Duration.ofMillis(retryProperties.getMaxBackoff()), retryProperties.getFactor(),
            true);

    return retryConfig;
  }
}
