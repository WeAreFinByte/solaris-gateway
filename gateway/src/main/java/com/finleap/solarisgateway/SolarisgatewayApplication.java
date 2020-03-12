package com.finleap.solarisgateway;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 *  Main Gateway application
 */
@Slf4j
@SpringBootApplication
public class SolarisgatewayApplication {

  public static void main(String[] args) {
    SpringApplication.run(SolarisgatewayApplication.class, args);
  }
}
