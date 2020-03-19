package com.finbyte.solarisgateway;

import static com.finbyte.solarisgateway.util.SolarisGatewayConstant.PROPERTIES_ROOT_PACKAGE;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

/**
 *  Main Gateway application
 */
@Slf4j
@SpringBootApplication
@ConfigurationPropertiesScan(PROPERTIES_ROOT_PACKAGE)
public class SolarisgatewayApplication {

  public static void main(String[] args) {
    SpringApplication.run(SolarisgatewayApplication.class, args);
  }
}
