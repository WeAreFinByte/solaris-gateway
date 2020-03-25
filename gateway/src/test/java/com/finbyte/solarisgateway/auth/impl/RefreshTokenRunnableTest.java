package com.finbyte.solarisgateway.auth.impl;

import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import com.finbyte.solarisgateway.auth.TokenService;
import com.finbyte.solarisgateway.auth.client.dto.GenericToken;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import reactor.core.publisher.Mono;


public class RefreshTokenRunnableTest {

  @InjectMocks
  private RefreshTokenRunnable refreshTokenRunnable;

  @Mock
  private TokenService tokenService;

  @Before
  public void init() {
    MockitoAnnotations.initMocks(this);
  }

  @Test
  public void testRefreshTokenRunnable() {

    Mono<GenericToken> genericTokenMono = Mono.empty();
    //Given
    doReturn(genericTokenMono).when(tokenService).refreshToken();

    //When
    refreshTokenRunnable.run();

    //Then
    verify(tokenService,times(1)).refreshToken();
  }

}