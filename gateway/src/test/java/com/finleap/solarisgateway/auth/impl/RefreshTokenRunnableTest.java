package com.finleap.solarisgateway.auth.impl;

import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import com.finleap.solarisgateway.auth.client.TokenClientService;
import com.finleap.solarisgateway.auth.client.dto.GenericToken;
import com.finleap.solarisgateway.auth.client.dto.Token;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
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

  @Test
  public void getClientShouldReturnUAAtokenClientWhenOauthProviderUAA() {

    //Given

    final String accessToken = "testToken";
    final Long expiresIn = 10L;

    final GenericToken token = new GenericToken();
    token.setAccessToken(accessToken);
    token.setExpiresIn(expiresIn);

    doReturn(token).when(tokenClientService).getToken();
    refreshTokenRunnable.setExpireThreshold(100L);

    //When
    refreshTokenRunnable.run();

    //Then
    verify(tokenClientService, times(1)).getToken();

    Token tokenInPool = TokenPool.getInstance().get();
    Assert.assertEquals(token, tokenInPool);

  }
}