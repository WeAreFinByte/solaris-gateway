package com.finbyte.solarisgateway.auth.impl;

import com.finbyte.solarisgateway.auth.client.TokenClientService;
import org.junit.Before;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;


public class RefreshTokenRunnableTest {

  @InjectMocks
  private RefreshTokenRunnable refreshTokenRunnable;

  @Mock
  private TokenClientService tokenClientService;

  @Before
  public void init() {
    MockitoAnnotations.initMocks(this);
  }

}