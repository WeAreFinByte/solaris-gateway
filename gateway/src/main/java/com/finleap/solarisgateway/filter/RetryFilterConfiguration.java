package com.finleap.solarisgateway.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.OrderedGatewayFilter;
import org.springframework.cloud.gateway.filter.factory.RetryGatewayFilterFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import static com.finleap.solarisgateway.util.SolarisGatewayConstant.Retry.RETRY_COUNT;

@Slf4j
@Component
public class RetryFilterConfiguration {

  @Value("${" + RETRY_COUNT + "}")
  private Integer retries;

  protected static int FILTER_ORDER = -1;

  @Bean("solarisRetryGatewayFilter")
  public GatewayFilter solarisRetryGatewayFilter(RetryGatewayFilterFactory retryGatewayFilterFactory) {
    return new OrderedGatewayFilter(retryGatewayFilterFactory.apply(getRetryConfig())
        , FILTER_ORDER);
  }

  // Retry Configuration
  protected RetryGatewayFilterFactory.RetryConfig getRetryConfig() {
    final RetryGatewayFilterFactory.RetryConfig retryConfig = new RetryGatewayFilterFactory.RetryConfig();
    retryConfig.setMethods(HttpMethod.values()).setRetries(retries).setStatuses(HttpStatus.UNAUTHORIZED);

    return retryConfig;
  }
}
