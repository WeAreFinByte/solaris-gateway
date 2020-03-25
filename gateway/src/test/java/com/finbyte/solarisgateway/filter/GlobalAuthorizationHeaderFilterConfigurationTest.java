package com.finbyte.solarisgateway.filter;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;

import com.finbyte.solarisgateway.auth.TokenService;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.http.HttpHeaders;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpRequest.Builder;
import org.springframework.web.server.ServerWebExchange;

@SpringBootTest
public class GlobalAuthorizationHeaderFilterConfigurationTest {

  @Mock
  private TokenService tokenService;

  @InjectMocks
  private GlobalAuthorizationHeaderFilterConfiguration globalFilterLoggingConfiguration = new GlobalAuthorizationHeaderFilterConfiguration();

  @Test
  public void globalAuthorizationHeaderFilterShouldCreateGlobalFilterTest() {
    //Given

    final ServerHttpResponse response = mock(ServerHttpResponse.class);
    final ServerWebExchange exchange = mock(ServerWebExchange.class);
    doReturn(response).when(exchange).getResponse();

    //When
    final GlobalFilter globalFilter = globalFilterLoggingConfiguration.globalAuthorizationHeaderFilter(tokenService);

    //Then
    Assert.assertNotNull(globalFilter);
  }

  @Test
  public void globalAuthorizationHeaderFilterShouldAddAuthHeaderTest() {
    //Given
    final String testToken = "testToken";
    doReturn(testToken).when(tokenService).getTokenWithJWTPrefix();
    final ServerHttpRequest request = mock(ServerHttpRequest.class);

    final Builder builder = mock(Builder.class);
    doReturn(builder).when(request).mutate();

    ArgumentCaptor<String> captor = ArgumentCaptor.forClass(String.class);
    doReturn(builder).when(builder).header(eq(HttpHeaders.AUTHORIZATION), captor.capture());
    doReturn(request).when(builder).build();

    final GatewayFilterChain chain = mock(GatewayFilterChain.class);

    final ServerWebExchange exchange = mock(ServerWebExchange.class);
    doReturn(request).when(exchange).getRequest();

    final ServerHttpResponse response = mock(ServerHttpResponse.class);
    doReturn(response).when(exchange).getResponse();

    final org.springframework.web.server.ServerWebExchange.Builder exhangeBuilder = mock(org.springframework.web.server.ServerWebExchange.Builder.class);
    doReturn(exhangeBuilder).when(exchange).mutate();
    doReturn(exhangeBuilder).when(exhangeBuilder).request(request);
    doReturn(exchange).when(exhangeBuilder).build();

    //When
    final GlobalFilter globalFilter = globalFilterLoggingConfiguration.globalAuthorizationHeaderFilter(tokenService);

    globalFilter.filter(exchange, chain);

    //Then
    Assert.assertNotNull(globalFilter);
    Assert.assertEquals(testToken, captor.getValue());
  }

}