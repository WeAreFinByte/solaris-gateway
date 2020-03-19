package com.finbyte.solarisgateway.util;

import com.finbyte.solarisgateway.util.SolarisGatewayConstant.Auth;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

// TODO move to properties
@Deprecated
@Component
public class EnvironmentProperties {

  public EnvironmentProperties(@Value("${" + Auth.PROVIDER_KEY + "}") String oauthProviderValue) {
    this.oauthProviderValue = oauthProviderValue;
  }

  private String oauthProviderValue;

  public OauthProvider getOauthProvider() {
    return OauthProvider.getOauthProvider(oauthProviderValue);
  }
}
