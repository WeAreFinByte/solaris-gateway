package com.finleap.solarisgateway.auth.listener;


import com.finleap.solarisgateway.auth.impl.RefreshTokenRunnable;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.context.event.ContextRefreshedEvent;

public class StartupApplicationTokenListenerTest {

  @InjectMocks
  private StartupApplicationTokenListener startupApplicationTokenListener;

  @Mock
  private RefreshTokenRunnable refreshTokenRunnable;

  @Mock
  private ContextRefreshedEvent event;

  @BeforeEach
  public void init() {
    startupApplicationTokenListener = new StartupApplicationTokenListener(refreshTokenRunnable);
    MockitoAnnotations.initMocks(this);
  }

  @Test
  public void globalAuthorizationHeaderFilterShouldCreateGlobalFilterTest() {
    //Given

    // When
    startupApplicationTokenListener.onApplicationEvent(event);

    //Then

  }
}