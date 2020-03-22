package com.finbyte.solarisgateway.auth.impl;

import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import com.finbyte.solarisgateway.util.EnvironmentProperties;
import com.finbyte.solarisgateway.auth.client.TokenClientService;
import com.finbyte.solarisgateway.auth.client.impl.SolarisTokenClientService;
import com.finbyte.solarisgateway.auth.client.impl.GeneralTokenClientService;
import com.finbyte.solarisgateway.util.OauthProvider;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

@SpringBootTest
public class TokenClientFactoryTest {

  @InjectMocks
  private TokenClientFactory tokenClientFactory = new TokenClientFactory();

  @Mock
  private ApplicationContext applicationContext;

  @Mock
  private EnvironmentProperties configuration;

  @Before
  public void init() {
    MockitoAnnotations.initMocks(this);
  }

  @Test
  public void getClientShouldReturnUAAtokenClientWhenOauthProviderUAA() {
    //Given

    doReturn(OauthProvider.UAA).when(configuration).getOauthProvider();

    final GeneralTokenClientService uaaTokenClientService = mock(GeneralTokenClientService.class);
    doReturn(uaaTokenClientService).when(applicationContext).getBean(GeneralTokenClientService.class);

    //When

    final TokenClientService client = tokenClientFactory.getClient();

    //Then
    Assert.assertNotNull(client);
    Assert.assertTrue(client instanceof GeneralTokenClientService);
    Assert.assertEquals(uaaTokenClientService, client);

    verify(applicationContext, times(1)).getBean(GeneralTokenClientService.class);

  }

  @Test
  public void getClientShouldReturnSolarisTokenClientWhenOauthProviderSolaris() {
    //Given

    doReturn(OauthProvider.SOLARIS).when(configuration).getOauthProvider();

    final SolarisTokenClientService solarisTokenClientService = mock(SolarisTokenClientService.class);
    doReturn(solarisTokenClientService).when(applicationContext).getBean(SolarisTokenClientService.class);

    //When
    final TokenClientService client = tokenClientFactory.getClient();

    //Then
    Assert.assertNotNull(client);
    Assert.assertTrue(client instanceof SolarisTokenClientService);
    Assert.assertEquals(solarisTokenClientService, client);

    verify(applicationContext, times(1)).getBean(SolarisTokenClientService.class);

  }

  @Test
  public void getClientShouldGiveExceptionWhenOauthProviderNotConfigured() {
    //Given

    doReturn(null).when(configuration).getOauthProvider();

    //When
    TokenClientService client = null;
    try {
      client = tokenClientFactory.getClient();

      fail("Test should give exception ");
    } catch (Exception ex) {

      Assert.assertTrue(ex instanceof NoSuchBeanDefinitionException);
    }

    //Then
    Assert.assertNull(client);
    Mockito.verifyNoInteractions(applicationContext);

  }
}