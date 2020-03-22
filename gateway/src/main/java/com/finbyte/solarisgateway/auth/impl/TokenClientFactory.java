package com.finbyte.solarisgateway.auth.impl;

import com.finbyte.solarisgateway.auth.client.TokenClientService;
import com.finbyte.solarisgateway.auth.client.impl.SolarisTokenClientService;
import com.finbyte.solarisgateway.util.EnvironmentProperties;
import com.finbyte.solarisgateway.util.OauthProvider;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class TokenClientFactory {

  @Autowired
  private ApplicationContext applicationContext;

  @Autowired
  private EnvironmentProperties configuration;

  @Bean("tokenClientService")
  public TokenClientService getClient() {

    final TokenClientService tokenClientService;

    final OauthProvider oauthProvider = configuration.getOauthProvider();

    if (oauthProvider != null) {
      switch (oauthProvider) {
        case MOCK:
          tokenClientService = applicationContext.getBean(SolarisTokenClientService.class);
          break;
        case SOLARIS:
          tokenClientService = applicationContext.getBean(SolarisTokenClientService.class);
          break;

        default:
          throw new NoSuchBeanDefinitionException("Auth client properties is wrong.");
      }
    } else {
      throw new NoSuchBeanDefinitionException("Auth client properties is not provided.");
    }

    return tokenClientService;
  }
}