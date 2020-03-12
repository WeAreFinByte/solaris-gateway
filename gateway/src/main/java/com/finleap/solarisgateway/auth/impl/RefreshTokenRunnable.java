package com.finleap.solarisgateway.auth.impl;

import static com.finleap.solarisgateway.util.SolarisGatewayConstant.Auth.THRESHOLD_KEY;

import com.finleap.solarisgateway.auth.client.TokenClientService;
import com.finleap.solarisgateway.auth.client.dto.GenericToken;
import com.finleap.solarisgateway.auth.client.dto.Token;
import javax.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class RefreshTokenRunnable implements Runnable {

  @Resource(name = "tokenClientService")
  private TokenClientService tokenClientService;

  @Value("${" + THRESHOLD_KEY + "}")
  private Long expireThreshold;

  private long expireTime;

  @SuppressWarnings("InfiniteLoopStatement")
  @Override
  public void run() {
    refreshToken();

    while (true) {
      long now = System.currentTimeMillis();

      if (now > expireTime) {
        refreshToken();
      }
      try {
        Thread.sleep(500);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
  }

  private void refreshToken() {

    final Token token = tokenClientService.getToken();
    final GenericToken genericToken = (GenericToken) token;
    setExpireTime(genericToken.getExpiresIn());
    TokenPool.getInstance().set(token);

    log.debug("Token refreshed expire time {}", expireTime);
  }

  private void setExpireTime(final Long expiresIn) {
    this.expireTime = System.currentTimeMillis() + (expiresIn * 100) - expireThreshold;
  }

}
