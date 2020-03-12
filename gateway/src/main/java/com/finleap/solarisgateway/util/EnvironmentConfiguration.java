package com.finleap.solarisgateway.util;

import com.finleap.solarisgateway.util.SolarisGatewayConstant.Auth;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class EnvironmentConfiguration {

  public EnvironmentConfiguration(@Value("${" + Auth.PROVIDER_KEY + "}") String oauthProviderValue) {
    this.oauthProviderValue = oauthProviderValue;
  }

  private String oauthProviderValue;

  public OauthProvider getOauthProvider() {
    return OauthProvider.getOauthProvider(oauthProviderValue);
  }
}
