package com.finleap.solarisgateway.auth.impl;

import com.finleap.solarisgateway.auth.client.dto.Token;

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
    _token = token;
  }

  public Token get() {
    return _token;
  }
}
