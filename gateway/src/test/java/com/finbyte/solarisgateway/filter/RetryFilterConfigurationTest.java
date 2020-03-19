package com.finbyte.solarisgateway.filter;

import static com.finbyte.solarisgateway.filter.RetryFilterConfiguration.FILTER_ORDER;
import static com.finbyte.solarisgateway.util.SolarisGatewayConstant.Retry.RETRY_COUNT;

import java.util.EnumSet;
import java.util.List;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.OrderedGatewayFilter;
import org.springframework.cloud.gateway.filter.factory.RetryGatewayFilterFactory;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.TestPropertySource;

@TestPropertySource(properties = RETRY_COUNT + "=" + 5)
@SpringBootTest(classes = {RetryGatewayFilterFactory.class, RetryFilterConfiguration.class})
public class RetryFilterConfigurationTest {

  @Autowired
  private RetryFilterConfiguration retryFilterConfiguration;

  @Autowired
  private RetryGatewayFilterFactory retryGatewayFilterFactory;

  @Test
  public void solarisRetryGatewayFilterTest() {
    //Given

    //When
    final GatewayFilter gatewayFilter = retryFilterConfiguration.solarisRetryGatewayFilter(retryGatewayFilterFactory);

    //Then
    Assert.assertNotNull(gatewayFilter);
    Assert.assertTrue(gatewayFilter instanceof OrderedGatewayFilter);
    Assert.assertEquals(FILTER_ORDER, ((OrderedGatewayFilter) gatewayFilter).getOrder());

  }

  @Test
  public void getRetryConfigTest() {
    //Given

    //When
    final RetryGatewayFilterFactory.RetryConfig retryConfig = retryFilterConfiguration.getRetryConfig();

    //Then
    Assert.assertNotNull(retryConfig);
    Assert.assertEquals(5, retryConfig.getRetries());

    final EnumSet<HttpStatus> expectedStatuses = EnumSet.of(HttpStatus.UNAUTHORIZED);
    final List<HttpStatus> statuses = retryConfig.getStatuses();

    Assert.assertTrue(statuses.containsAll(expectedStatuses));

    final EnumSet<HttpMethod> expectedHttpMethods = EnumSet.allOf(HttpMethod.class);
    final List<HttpMethod> methods = retryConfig.getMethods();

    Assert.assertTrue(methods.containsAll(expectedHttpMethods));

  }
}