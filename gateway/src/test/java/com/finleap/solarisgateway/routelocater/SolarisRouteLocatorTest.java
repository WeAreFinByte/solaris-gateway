package com.finleap.solarisgateway.routelocater;

import static com.finleap.solarisgateway.util.SolarisGatewayConstant.RouteLocator.DEFAULT_PREFIX_KEY;
import static com.finleap.solarisgateway.util.SolarisGatewayConstant.RouteLocator.DEFAULT_URI_KEY;

import java.util.List;
import javax.annotation.Resource;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.route.Route;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.test.context.TestPropertySource;

@TestPropertySource(properties = {DEFAULT_URI_KEY + "=http://resource-server", DEFAULT_PREFIX_KEY + "=/testprefix/"})
@SpringBootTest
public class SolarisRouteLocatorTest {

  @Resource(name = "solarisRouteLocator")
  private SolarisRouteLocator solarisRouteLocator;

  @Autowired
  private RouteLocatorBuilder builder;

  @Test
  public void customRouteLocatorShouldNotNullTest() {
    final RouteLocator routeLocator = solarisRouteLocator.customRouteLocator(builder);

    Assert.assertNotNull(routeLocator);
  }

  @Test
  public void customRouteLocatorRouterTest() {
    // Given

    //When
    final RouteLocator routeLocator = solarisRouteLocator.customRouteLocator(builder);

    //Then
    Assert.assertEquals("http://resource-server",solarisRouteLocator.getUri());
    Assert.assertEquals("/testprefix/",solarisRouteLocator.getPrefix());

    Assert.assertNotNull(routeLocator);
    final Route route = routeLocator.getRoutes().blockFirst();

    Assert.assertNotNull(route);
    final List<GatewayFilter> filters = route.getFilters();
    Assert.assertNotNull(filters);
    Assert.assertEquals(2, filters.size());

    final String routeId = route.getId();
    Assert.assertEquals(SolarisRouteLocator.ROUTER_ID, routeId);

  }
}