package com.finbyte.solarisgateway.util;

import com.finbyte.solarisgateway.util.SolarisGatewayConstant.AuthProviderEnumValue;
import lombok.Getter;

/**
 * Available Oauth Provider List
 */
public enum OauthProvider {

  SOLARIS(AuthProviderEnumValue.SOLARIS),//
  UAA(AuthProviderEnumValue.UAA);//

  @Getter
  private final String providerKey;

  OauthProvider(String providerKey) {
    this.providerKey = providerKey;
  }

  public static OauthProvider getOauthProvider(String providerKey) {

    OauthProvider oauthProvider = null;
    switch (providerKey) {
      case AuthProviderEnumValue.UAA:
        oauthProvider = UAA;
        break;
      case AuthProviderEnumValue.SOLARIS:
        oauthProvider = SOLARIS;
        break;
    }

    return oauthProvider;
  }
}
