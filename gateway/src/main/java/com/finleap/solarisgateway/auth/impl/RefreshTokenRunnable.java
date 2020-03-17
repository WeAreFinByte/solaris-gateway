package com.finleap.solarisgateway.auth.impl;

import static com.finleap.solarisgateway.util.SolarisGatewayConstant.Auth.THRESHOLD_KEY;

import com.finleap.solarisgateway.auth.client.TokenClientService;
import com.finleap.solarisgateway.auth.client.dto.GenericToken;
import com.finleap.solarisgateway.auth.client.dto.Token;
import javax.annotation.Resource;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class RefreshTokenRunnable implements Runnable {

  @Resource(name = "tokenClientService")
  private TokenClientService tokenClientService;

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

    try {
      final Token token = tokenClientService.getToken();
      final GenericToken genericToken = (GenericToken) token;
      setExpireTime(genericToken.getExpiresIn());
      TokenPool.getInstance().set(token);

      log.debug("Token refreshed expire time {}", expireTime);

    } catch (Throwable t) {
      log.error("Token cannot be get from provider  " + t.getMessage());
    }
  }

  private void setExpireTime(final Long expiresIn) {
    this.expireTime = System.currentTimeMillis() + (expiresIn * 100) - expireThreshold;
  }

}
