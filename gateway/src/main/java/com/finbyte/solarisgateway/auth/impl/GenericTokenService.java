package com.finbyte.solarisgateway.auth.impl;

import com.finbyte.solarisgateway.auth.TokenService;
import com.finbyte.solarisgateway.auth.client.TokenClientService;
import com.finbyte.solarisgateway.auth.client.dto.GenericToken;
import com.finbyte.solarisgateway.auth.client.dto.Token;
import com.finbyte.solarisgateway.util.SolarisGatewayConstant.Auth;
import javax.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Slf4j
@Component("genericTokenService")
public class GenericTokenService implements TokenService {

  @Resource(name = "tokenClientService")
  private TokenClientService tokenClientService;

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

  @Override
  public Mono<GenericToken> refreshToken() {
    return tokenClientService.getToken() //
        .doOnSuccess(token -> TokenPool.getInstance().set(token));
  }
}
