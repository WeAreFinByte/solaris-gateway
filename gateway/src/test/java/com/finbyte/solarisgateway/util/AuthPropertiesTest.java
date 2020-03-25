package com.finbyte.solarisgateway.util;

import static com.finbyte.solarisgateway.util.SolarisGatewayConstant.Auth.PROVIDER_KEY;

import com.finbyte.solarisgateway.util.SolarisGatewayConstant.AuthProviderEnumValue;
import com.finbyte.solarisgateway.util.properties.AuthProperties;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

@TestPropertySource(properties = PROVIDER_KEY + "=" + AuthProviderEnumValue.SOLARIS)
@SpringBootTest
public class AuthPropertiesTest {

  @Autowired
  private AuthProperties environmentProperties;

  @Test
  public void getOauthProviderTest() {
    Assert.assertEquals(OauthProvider.SOLARIS, environmentProperties.getOauthProvider());
  }

}

