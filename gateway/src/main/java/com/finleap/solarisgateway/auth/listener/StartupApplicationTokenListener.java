package com.finleap.solarisgateway.auth.listener;

import com.finleap.solarisgateway.auth.impl.RefreshTokenRunnable;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

/**
 * Listener start a thread to get and refresh token from auth provider
 *
 * @see com.finleap.solarisgateway.auth.impl.RefreshTokenRunnable#refreshToken
 */
@Component
public class StartupApplicationTokenListener implements
    ApplicationListener<ContextRefreshedEvent> {

  private ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(1);

  private RefreshTokenRunnable refreshTokenRunnable;

  public StartupApplicationTokenListener(final RefreshTokenRunnable refreshTokenRunnable) {
    this.refreshTokenRunnable = refreshTokenRunnable;
  }

  @Override
  public void onApplicationEvent(ContextRefreshedEvent event) {
    executor.execute(refreshTokenRunnable);
  }
}