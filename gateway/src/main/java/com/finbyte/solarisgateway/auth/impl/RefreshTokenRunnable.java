package com.finbyte.solarisgateway.auth.impl;

import static com.finbyte.solarisgateway.util.SolarisGatewayConstant.Auth.THRESHOLD_KEY;

import com.finbyte.solarisgateway.auth.TokenService;
import com.finbyte.solarisgateway.auth.client.dto.GenericToken;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Slf4j
@Component
public class RefreshTokenRunnable implements Runnable {

  @Autowired
  @Qualifier("genericTokenService")
  private TokenService tokenService;

  @Setter
  @Value("${" + THRESHOLD_KEY + "}")
  private Long expireThreshold;

  private long expireTime;

  @Override
  public void run() {
    long now = System.currentTimeMillis();

    if (now > expireTime) {
      refreshToken();
    }
  }

  private void refreshToken() {

    final Mono<GenericToken> tokenPublisher = tokenService.refreshToken();

    tokenPublisher//
        .doOnError(t -> log.error("Token cannot be get from provider  " + t.getMessage()))//
        .subscribe(token -> {
          setExpireTime(token.getExpiresIn());
          log.debug("Token refreshed expire time {}", expireTime);
        });
  }

  private void setExpireTime(final Long expiresIn) {
    this.expireTime = System.currentTimeMillis() + (expiresIn * 100) - expireThreshold;
  }

}
