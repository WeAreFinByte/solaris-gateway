package com.finleap.solarisgateway.auth.impl;

import com.finleap.solarisgateway.auth.client.TokenClientService;
import com.finleap.solarisgateway.auth.client.impl.SolarisTokenClientService;
import com.finleap.solarisgateway.auth.client.impl.UaaTokenClientService;
import com.finleap.solarisgateway.util.EnvironmentConfiguration;
import com.finleap.solarisgateway.util.OauthProvider;
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
  private EnvironmentConfiguration configuration;

  @Bean("tokenClientService")
  public TokenClientService getClient() {

    final TokenClientService tokenClientService;

    final OauthProvider oauthProvider = configuration.getOauthProvider();

    switch (oauthProvider) {
      case UAA:
        tokenClientService = applicationContext.getBean(UaaTokenClientService.class);
        break;
      case SOLARIS:
        tokenClientService = applicationContext.getBean(SolarisTokenClientService.class);
        break;

      default:
        throw new NoSuchBeanDefinitionException("Auth client properties is not provided.");
    }

    return tokenClientService;
  }
}