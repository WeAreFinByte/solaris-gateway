package com.finbyte.solarisgateway.auth.impl;

import com.finbyte.solarisgateway.auth.client.dto.Token;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TokenPool {

  private Token _token;

  private TokenPool() {
    // private constructor
  }

  private static class TokenPoolSingleton {

    private static final TokenPool INSTANCE = new TokenPool();
  }

  public static TokenPool getInstance() {
    return TokenPoolSingleton.INSTANCE;
  }

  protected void set(Token token) {
    log.debug("Token refreshed");
    _token = token;
  }

  public Token get() {
    return _token;
  }
}
