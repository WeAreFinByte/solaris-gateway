package com.finbyte.solarisgateway.util;

import com.finbyte.solarisgateway.util.SolarisGatewayConstant.AuthProviderEnumValue;
import lombok.Getter;

/**
 * Available Oauth Provider List
 */
public enum OauthProvider {

  SOLARIS(AuthProviderEnumValue.SOLARIS),//
  MOCK(AuthProviderEnumValue.MOCK);//



  @Getter
  private final String providerKey;

  OauthProvider(String providerKey) {
    this.providerKey = providerKey;
  }

  public static OauthProvider getOauthProvider(String providerKey) {

    OauthProvider oauthProvider = null;
    switch (providerKey) {
      case AuthProviderEnumValue.SOLARIS:
        oauthProvider = SOLARIS;
        break;
      case AuthProviderEnumValue.MOCK:
        oauthProvider = MOCK;
        break;
    }

    return oauthProvider;
  }
}
