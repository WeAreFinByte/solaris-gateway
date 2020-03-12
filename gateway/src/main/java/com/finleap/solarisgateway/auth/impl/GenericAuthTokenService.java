package com.finleap.solarisgateway.auth.impl;

import com.finleap.solarisgateway.auth.AuthTokenService;
import com.finleap.solarisgateway.auth.client.dto.GenericToken;
import com.finleap.solarisgateway.auth.client.dto.Token;
import com.finleap.solarisgateway.util.SolarisGatewayConstant.Auth;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component("genericAuthTokenService")
public class GenericAuthTokenService implements AuthTokenService {

  @Override
  public String getToken() {

    final Token token = TokenPool.getInstance().get();

    if (null == token) {
      log.warn("Authorization Token is empty. please check project config");
    }

    return null != token ? ((GenericToken) token).getAccessToken() : "";
  }

  @Override
  public String getTokenWithJWTPrefix() {
    return Auth.JWT_PREFIX + getToken();
  }

}
